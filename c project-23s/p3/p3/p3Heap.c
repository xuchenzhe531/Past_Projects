///////////////////////////////////////////////////////////////////////////////
//
// Copyright 2020-2023 Deb Deppeler based on work by Jim Skrentny
// Posting or sharing this file is prohibited, including any changes/additions.
// Used by permission SPRING 2023, CS354-deppeler
//
///////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
// Main File:        p3Heap.c
// This File:        p3Heap.c
// Other Files:      
// Semester:         CS 354 Lecture 002 Spring 2023
// Instructor:       deppeler
// 
// Author:           Lucas Xu
// Email:            cxu296@wisc.edu
// CS Login:         chenzhe
//
/////////////////////////// OTHER SOURCES OF HELP //////////////////////////////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide ///////////////////////////////////
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <stdio.h>
#include <string.h>
#include "p3Heap.h"
#include <stdbool.h>
 
/*
 * This structure serves as the header for each allocated and free block.
 * It also serves as the footer for each free block but only containing size.
 */
typedef struct blockHeader {           

    int size_status;

    /*
     * Size of the block is always a multiple of 8.
     * Size is stored in all block headers and in free block footers.
     *
     * Status is stored only in headers using the two least significant bits.
     *   Bit0 => least significant bit, last bit
     *   Bit0 == 0 => free block
     *   Bit0 == 1 => allocated block
     *
     *   Bit1 => second last bit 
     *   Bit1 == 0 => previous block is free
     *   Bit1 == 1 => previous block is allocated
     * 
     * Start Heap: 
     *  The blockHeader for the first block of the heap is after skip 4 bytes.
     *  This ensures alignment requirements can be met.
     * 
     * End Mark: 
     *  The end of the available memory is indicated using a size_status of 1.
     * 
     * Examples:
     *   11001
     * 1. Allocated block of size 24 bytes:
     *    Allocated Block Header:
     *      If the previous block is free      p-bit=0 size_status would be 25 -->11001
     *      If the previous block is allocated p-bit=1 size_status would be 27 -->11011
     * 
     * 2. Free block of size 24 bytes:
     *    Free Block Header:
     *      If the previous block is free      p-bit=0 size_status would be 24
     *      If the previous block is allocated p-bit=1 size_status would be 26
     *    Free Block Footer:
     *      size_status should be 24
     */
} blockHeader;         

/* Global variable - DO NOT CHANGE NAME or TYPE. 
 * It must point to the first block in the heap and is set by init_heap()
 * i.e., the block at the lowest address.
 */
blockHeader *heap_start = NULL;     

/* Size of heap allocation padded to round to nearest page size.
 */
int alloc_size;

/*
 * Additional global variables may be added as needed below
 * TODO: add global variables needed by your function
 */

 
/* 
 * Function for allocating 'size' bytes of heap memory.
 * Argument size: requested size for the payload
 * Returns address of allocated block (payload) on success.
 * Returns NULL on failure.
 *
 * This function must:
 * - Check size - Return NULL if size < 1 
 * - Determine block size rounding up to a multiple of 8 
 *   and possibly adding padding as a result.
 *
 * - Use BEST-FIT PLACEMENT POLICY to chose a free block
 *
 * - If the BEST-FIT block that is found is exact size match
 *   - 1. Update all heap blocks as needed for any affected blocks
 *   - 2. Return the address of the allocated block payload
 *
 * - If the BEST-FIT block that is found is large enough to split 
 *   - 1. SPLIT the free block into two valid heap blocks:
 *         1. an allocated block
 *         2. a free block
 *         NOTE: both blocks must meet heap block requirements 
 *       - Update all heap block header(s) and footer(s) 
 *              as needed for any affected blocks.
 *   - 2. Return the address of the allocated block payload
 *
 *   Return if NULL unable to find and allocate block for required size
 *
 * Note: payload address that is returned is NOT the address of the
 *       block header.  It is the address of the start of the 
 *       available memory for the requesterr.
 *
 * Tips: Be careful with pointer arithmetic and scale factors.
 */
void* balloc(int size) {  
    //TODO: Your code goes in here.
    //Check size - Return NULL if size < 1 
    if(size < 1){
      printf("%s\n","Error: balloc() size input is less than 1");
      return NULL;
    }
  //Determine block size rounding up to a multiple of 8 
  //and possibly adding padding as a result.
  int header = sizeof(blockHeader); //Size of blockheader which is 4
  int modulus = (size+header)%8; //check whether the sum of allocate size and header is multiple of 8
  int padding; //padding to make the number as the multiple of 8
  //Check whether the sum of size and header is multiple of 8
  if(modulus == 0){
    padding = 0;
  }else{
    padding = 8-modulus;
  }
  int total = padding + header+size; //Total size that is multiple of 8
  blockHeader *current = heap_start; //Get the first block

  int t_size; //size status of the current block
  int difference = alloc_size; //set the default for the situation when splitting is needed
  char* addr = NULL; //address of the closest block when use best-fit strategy
  bool split_checker = false; //Check whether splitting is needed
  blockHeader * track; //Track the block that will be splitted
  //Loop through the whole heap
  while(current->size_status != 1){
        t_size = current->size_status;
        int ap_value = 0;
        //Check whether current block is allocated
        if (t_size & 1){
            t_size = t_size - 1;
            ap_value +=1;
        }
        //Check whether previous block is allocated
        if (t_size & 2) {
            t_size = t_size - 2;
            ap_value +=2;
        }
        //If current block is allocated
        if(ap_value == 1 || ap_value == 3){
            current = (blockHeader*)((char*)current + t_size);
            continue;
        //If previous block is allocated but current block is not
        }if(ap_value == 2){
            if(total == t_size){
                current->size_status = total+3;
                //Set the bit 1 of next block to be 1
                //Set footer
                blockHeader *footer = (blockHeader*) ((void*)current + total - sizeof(blockHeader));
                footer->size_status = total;
                char* temp = ((char*)current+header);
                //Change to next block
                current = (blockHeader*)((char*)current + t_size);
                //If the next block is not end mark
                if(current->size_status != 1){
                    current->size_status +=2;
                }
                return temp;
            }else if(total < t_size){
                split_checker = true;
                //Track the smallest difference between size and block size
                if(t_size - total < difference){
                    difference = t_size - total;
                    addr = ((char*)current);
                    track = (blockHeader*)(addr);
                }
                //Change to next block
                current = (blockHeader*)((char*)current + t_size);
            }else{
                //Change to next block
                current = (blockHeader*)((char*)current + t_size);
            }
        //If previous block is not allocated and current block is not allocated
        }else{
            if(total == t_size){
                current->size_status = total+1;
                //Set the bit 1 of next block to be 1
                //Set footer
                blockHeader *footer = (blockHeader*) ((void*)current + total - sizeof(blockHeader));
                footer->size_status = total;
                char* temp = ((char*)current+header);
                //Change to next block
                current = (blockHeader*)((char*)current + t_size);
                //If the next block is not end mark
                if(current->size_status != 1){
                    current->size_status +=2;
                }
                return temp;
            }else if(total< t_size){
                split_checker = true;
                //Track the smallest difference between size and block size
                if(t_size - total < difference){
                    difference = t_size - total;
                    addr = ((char*)current);
                    track = (blockHeader*)(addr);
                }
                //Change to next block
                current = (blockHeader*)((char*)current + t_size);
            }else{
                //Change to next block
                current = (blockHeader*)((char*)current + t_size);
            }
        }
    }
    int ap_value_two = 0;
    if(split_checker == true){
        int current_size = track->size_status;
        //Check whether current block is allocated
        if (current_size & 1){
            current_size = current_size - 1;
            ap_value_two +=1;
        }
        //Check whether previous block is allocated
        if (current_size & 2) {
            current_size = current_size - 2;
            ap_value_two +=2;
        }
        track->size_status = total+ap_value_two+1;
        char* return_val = ((char*)track+header);
        char* ptr =((char*)track+total);
        blockHeader *  new_start = (blockHeader*) ptr;
        if(new_start->size_status != 1){
            new_start->size_status += 2;
        }
        int new_start_size = current_size - total;
        new_start->size_status += new_start_size;
        //Set footer
        blockHeader *footer = (blockHeader*) ((void*)track + total - sizeof(blockHeader));
        footer->size_status = total;
        return return_val;
    }
    printf("%s\n", "Error: balloc() unable to find and allocate block for required size");
    return NULL;
 }
 
/* 
 * Function for freeing up a previously allocated block.
 * Argument ptr: address of the block to be freed up.
 * Returns 0 on success.
 * Returns -1 on failure.
 * This function should:
 * - Return -1 if ptr is NULL.
 * - Return -1 if ptr is not a multiple of 8.
 * - Return -1 if ptr is outside of the heap space.
 * - Return -1 if ptr block is already freed.
 * - Update header(s) and footer as needed.
 */                    
int bfree(void *ptr) {    
    //Return -1 if ptr is NULL.
    if(ptr == NULL){
        printf("%s\n", "Error: bfree() pointer is null\n");
        return -1;
    }
    //Return -1 if ptr is not a multiple of 8.
    int last_digit = (int)ptr &0xf;
    if(last_digit == 0 || last_digit == 8){

    }else{
        printf("%s\n", "Error: bfree() pointer is not multiple of 8");
        return -1;
    }
    //Return -1 if ptr is outside of the heap space.
    void* last_space = (void*)heap_start+alloc_size-4;
    if(last_space < ptr){
        printf("%s\n", "Error: bfree() pointer is outside of the heap space");
        return -1;
    }
    //Return -1 if ptr block is already freed.
    blockHeader* head = (blockHeader*) ((void*)ptr - sizeof(blockHeader));
    int size_ptr = head->size_status;
    if(size_ptr & 1){
        
    }else{
        printf("%s\n", "Error: bfree() ptr block is already freed");
        return -1;
    }
    head->size_status = head->size_status-1;
    int t_size = head->size_status;
    int ap_value = 0;
    //Check whether current block is allocated
    if (t_size & 1){
        t_size = t_size - 1;
        ap_value +=1;
    }
    //Check whether previous block is allocated
    if (t_size & 2) {
        t_size = t_size - 2;
        ap_value +=2;
    }
    //Set footer
    blockHeader *footer = (blockHeader*) ((void*)head + t_size - sizeof(blockHeader));
    footer->size_status = 0;
    blockHeader *next = (blockHeader*)((char*)head + t_size);
    if(next->size_status != 1){
        next->size_status = next->size_status-2;
    }

    return 0;
} 

/*
 * Function for traversing heap block list and coalescing all adjacent 
 * free blocks.
 *
 * This function is used for user-called coalescing.
 * Updated header size_status and footer size_status as needed.
 */
int coalesce() {
    blockHeader *current = heap_start; //Get the first block
    int t_size; //size status of the current block
    int ap_value = 0;
    blockHeader * track; //Track the first block that is empty
    int coalesce_total = 0; //Total number for coalsescing
    bool coalesce_check = false; //Check whether coalescing is needed
    while(current->size_status != 1){
        t_size = current->size_status;
        ap_value = 0;
        //Check whether current block is allocated
        if (t_size & 1){
            t_size = t_size - 1;
            ap_value +=1;
        }
        //Check whether previous block is allocated
        if (t_size & 2) {
            t_size = t_size - 2;
            ap_value +=2;
        }
        //If current block is not allocated and the previous one is allocated
        if(ap_value == 2){
            track = current;
            coalesce_total +=t_size; //Count the size of this block
            //Check whether the next block is empty in order to decide coalescing is needed
            blockHeader* next = (blockHeader*)((char*)current + t_size);
            if(next->size_status & 1){
                //skip
            }else{
                //Remove footer
                blockHeader *footer = (blockHeader*) ((void*)current + t_size - sizeof(blockHeader));
                footer->size_status = 0; 
            }
            current = (blockHeader*)((char*)current + t_size);
        //If current block is not allocated and previous one is not as well
        }else if(ap_value == 0){
            //Coalesce will happen
            coalesce_check = true;
            coalesce_total +=t_size;
            current->size_status=0;
            //Remove footer
            blockHeader *footer = (blockHeader*) ((void*)current + t_size - sizeof(blockHeader));
            footer->size_status = 0;
            current = (blockHeader*)((char*)current + t_size);
        }else{
            if(coalesce_check == true){
                //Assign the new size_status to the combined blocks
                track->size_status = coalesce_total+2;
                blockHeader *footer = (blockHeader*) ((void*)track+ coalesce_total - sizeof(blockHeader));
                footer->size_status = coalesce_total;
            }
            //Set data to default
            coalesce_check = false;
            coalesce_total = 0;
            track = NULL;
            current = (blockHeader*)((char*)current + t_size);
        }
    }
    //Return 0 if no adjacent space to combine, 1 if there is adjacent space to combine
    if(coalesce_check == true){
        return 1;
    }else{
        printf("%s\n", "Error: coalesce() there is no adjacent blocks");
	    return 0;
    }
}

 
/* 
 * Function used to initialize the memory allocator.
 * Intended to be called ONLY once by a program.
 * Argument sizeOfRegion: the size of the heap space to be allocated.
 * Returns 0 on success.
 * Returns -1 on failure.
 */                    
int init_heap(int sizeOfRegion) {    
 
    static int allocated_once = 0; //prevent multiple myInit calls
 
    int   pagesize; // page size
    int   padsize;  // size of padding when heap size not a multiple of page size
    void* mmap_ptr; // pointer to memory mapped area
    int   fd;

    blockHeader* end_mark;
  
    if (0 != allocated_once) {
        fprintf(stderr, 
        "Error:mem.c: InitHeap has allocated space during a previous call\n");
        return -1;
    }

    if (sizeOfRegion <= 0) {
        fprintf(stderr, "Error:mem.c: Requested block size is not positive\n");
        return -1;
    }

    // Get the pagesize from O.S. 
    pagesize = getpagesize();

    // Calculate padsize as the padding required to round up sizeOfRegion 
    // to a multiple of pagesize
    padsize = sizeOfRegion % pagesize;
    padsize = (pagesize - padsize) % pagesize;

    alloc_size = sizeOfRegion + padsize;

    // Using mmap to allocate memory
    fd = open("/dev/zero", O_RDWR);
    if (-1 == fd) {
        fprintf(stderr, "Error:mem.c: Cannot open /dev/zero\n");
        return -1;
    }
    mmap_ptr = mmap(NULL, alloc_size, PROT_READ | PROT_WRITE, MAP_PRIVATE, fd, 0);
    if (MAP_FAILED == mmap_ptr) {
        fprintf(stderr, "Error:mem.c: mmap cannot allocate space\n");
        allocated_once = 0;
        return -1;
    }
  
    allocated_once = 1;

    // for double word alignment and end mark
    alloc_size -= 8;

    // Initially there is only one big free block in the heap.
    // Skip first 4 bytes for double word alignment requirement.
    heap_start = (blockHeader*) mmap_ptr + 1;

    // Set the end mark
    end_mark = (blockHeader*)((void*)heap_start + alloc_size);
    end_mark->size_status = 1;

    // Set size in header
    heap_start->size_status = alloc_size;

    // Set p-bit as allocated in header
    // note a-bit left at 0 for free
    heap_start->size_status += 2;

    // Set the footer
    blockHeader *footer = (blockHeader*) ((void*)heap_start + alloc_size - 4);
    footer->size_status = alloc_size;
  
    return 0;
} 
                  
/* 
 * Function can be used for DEBUGGING to help you visualize your heap structure.
 * Traverses heap blocks and prints info about each block found.
 * 
 * Prints out a list of all the blocks including this information:
 * No.      : serial number of the block 
 * Status   : free/used (allocated)
 * Prev     : status of previous block free/used (allocated)
 * t_Begin  : address of the first byte in the block (where the header starts) 
 * t_End    : address of the last byte in the block 
 * t_Size   : size of the block as stored in the block header
 */                     
void disp_heap() {     
 
    int    counter;
    char   status[6];
    char   p_status[6];
    char * t_begin = NULL;
    char * t_end   = NULL;
    int    t_size;

    blockHeader *current = heap_start;
    counter = 1;

    int used_size =  0;
    int free_size =  0;
    int is_used   = -1;

    fprintf(stdout, 
	"*********************************** HEAP: Block List ****************************\n");
    fprintf(stdout, "No.\tStatus\tPrev\tt_Begin\t\tt_End\t\tt_Size\n");
    fprintf(stdout, 
	"---------------------------------------------------------------------------------\n");
    // |00 01 02 03 |04 05 06 07 08 09 0A 0B |0C 0D 0E 0F|
    //t_begin = 0x04
    //t_size = 8+1+2=11
    //t_size = 10
    //t_size = 8
    //used_size = 8
    //current = 0x04+8 = 0x0C
    while (current->size_status != 1) {
        t_begin = (char*)current;
        t_size = current->size_status;
    
        if (t_size & 1) {
            // LSB = 1 => used block
            strcpy(status, "alloc");
            is_used = 1;
            t_size = t_size - 1;
        } else {
            strcpy(status, "FREE ");
            is_used = 0;
        }

        if (t_size & 2) {
            strcpy(p_status, "alloc");
            t_size = t_size - 2;
        } else {
            strcpy(p_status, "FREE ");
        }

        if (is_used) 
            used_size += t_size;
        else 
            free_size += t_size;

        t_end = t_begin + t_size - 1;
    
        fprintf(stdout, "%d\t%s\t%s\t0x%08lx\t0x%08lx\t%4i\n", counter, status, 
        p_status, (unsigned long int)t_begin, (unsigned long int)t_end, t_size);
    
        current = (blockHeader*)((char*)current + t_size);
        counter = counter + 1;
    }

    fprintf(stdout, 
	"---------------------------------------------------------------------------------\n");
    fprintf(stdout, 
	"*********************************************************************************\n");
    fprintf(stdout, "Total used size = %4d\n", used_size);
    fprintf(stdout, "Total free size = %4d\n", free_size);
    fprintf(stdout, "Total size      = %4d\n", used_size + free_size);
    fprintf(stdout, 
	"*********************************************************************************\n");
    fflush(stdout);

    return;  
} 


// end p3Heap.c (Spring 2023)                                         


