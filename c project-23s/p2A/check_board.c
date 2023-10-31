///////////////////////////////////////////////////////////////////////////////
//
// Copyright 2021 Deb Deppeler
// Posting or sharing this file is prohibited, including any changes/additions.
//
// We have provided comments and structure for this program to help you get 
// started.  Later programs will not provide the same level of commenting,
// rather you will be expected to add same level of comments to your work.
// 09/20/2021 Revised to free memory allocated in get_board_size function.
// 01/24/2022 Revised to use pointers for CLAs
//
////////////////////////////////////////////////////////////////////////////////
// Main File:        check_board.c
// This File:        check_board.c
// Other Files:      (name of all other files if any)
// Semester:         CS 354 Spring 2023
// Instructor:       Debra Deppler
//
// Author:           Chenzhe Xu
// Email:            cxu296@wisc.edu
// CS Login:         chenzhe
// GG#:              12
//                   (See https://canvas.wisc.edu/groups for your GG number)
//
/////////////////////////// OTHER SOURCES OF HELP //////////////////////////////
//                   Fully acknowledge and credit all sources of help,
//                   including family, friencs, classmates, tutors,
//                   Peer Mentors, TAs, and Instructor.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   Avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of
//                   of any information you find.
////////////////////////////////////////////////////////////////////////////////

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *DELIM = ",";  // commas ',' are a common delimiter character for data strings

/* COMPLETED (DO NOT EDIT):       
 * Read the first line of input file to get the size of that board.
 * 
 * PRE-CONDITION #1: file exists
 * PRE-CONDITION #2: first line of file contains valid non-zero integer value
 *
 * fptr: file pointer for the board's input file
 * size: a pointer to an int to store the size
 *
 * POST-CONDITION: the integer whos address is passed in as size (int *) 
 * will now have the size (number of rows and cols) of the board being checked.
 */
void get_board_size(FILE *fptr, int *size) {      
	char *line1 = NULL;
	size_t len = 0;

	if ( getline(&line1, &len, fptr) == -1 ) {
		printf("Error reading the input file.\n");
		free(line1);
		exit(1);
	}

	char *size_chars = NULL;
	size_chars = strtok(line1, DELIM);
	*size = atoi(size_chars);

	// free memory allocated for reading first link of file
	free(line1);
	line1 = NULL;
}



/* Returns 1 if and only if the board is in a valid Sudoku board state.
 * Otherwise returns 0. A valid row or column contains only blanks or the digits 1-size, 
 * with no duplicate digits, where size is the value 1 to 9.
 * p2A requires only that each row and each column are valid.
 * 
 * Pre-conditions#1: board is an allocated 2D array
 * Pre-conditions#2: all contents are stored in board
 *
 * board: heap allocated 2D array of integers 
 * size:  number of rows and columns in the board
 *
 * Return 0 is the board is not valid, 1 if the board is valid
 */
int valid_board(int **board, int size) {
  //Horizontal Check
  for(int i = 0; i < size; i++){
    //This array is used to check whether the duplicate number exists horizontally
    char*horizontal_check = malloc((size+1)*sizeof(char));
    if(horizontal_check == NULL){
    printf("Not Enough Memory\n");
    exit(1);
    }
  //Al
    for (int j = 0; j < size; j++){
      //If the element at the given index is 0, just skip
      if(*(*(board+i)+j) == 0){
        continue;
      }else{
        //If the element in horizontal_check is already an X, it indicates that
        //this number has been checked before, so there is a duplicate for the number
        if(*(horizontal_check + *(*(board+i)+j)) == 'X'){
          return 0;
          //If the element in horizontal_check index is not 'X', it indicates that this is
          //the first time the number is appearing
        }else{
          *(horizontal_check + *(*(board+i)+j)) = 'X';
        }
      }
    }
    // free memory allocated for horizontal check array
    free(horizontal_check);
  }
  //Vertical Check
  for(int m = 0; m < size; m++){
    //This array is used to check whether the duplicate number exists vertically
    char*vertical_check = malloc((size+1)*sizeof(char));
    if(vertical_check == NULL){
    printf("Not Enough Memory\n");
    exit(1);
    }
    for(int n = 0; n < size; n++){
      //This array is used to check whether the duplicate number exists vertically
      if(*(*(board+n)+m) == 0){
        continue;
      }else{
        //If the element in vertical_check is already an X, it indicates that
        //this number has been checked before, so there is a duplicate for the number
        if(*(vertical_check + *(*(board+n)+m)) == 'X'){
          return 0;
        }else{
          //If the element in vertical_check index is not 'X', it indicates that this is
          //the first time the number is appearing
          *(vertical_check + *(*(board+n)+m)) = 'X';
        }
      }
    }
     // free memory allocated for vertical check array
    free(vertical_check);
  }
  //return 1 if the board is valid
	return 1;   
}    



/* This program prints "valid" (without quotes) if the input file contains
 * a valid state of a Sudoku puzzle board wrt to rows and columns only.
 *
 * Precondition#1: A single CLA is required, which is the name of the file 
 * that contains board data is required.
 *
 * argc: the number of command line args (CLAs)
 * argv: the CLA strings, includes the program name
 *
 * Returns 0 if able to correctly output valid or invalid.
 * Only exit with a non-zero result if unable to open and read the file given.
 */
int main( int argc, char **argv ) {              

	// Check if number of command-line arguments is correct.
  if(argc <= 1){
    printf("There is only one argument without any file\n");
    exit(1);
  }
  if(argc>2){
    printf("The argument is more than 2\n");
    exit(1);
  }
  
	// Open the file and check if it opened successfully.
	FILE *fp = fopen(*(argv + 1), "r");
	if (fp == NULL) {
		printf("Can't open file for reading.\n");
		exit(1);
	}

	// Declare local variables.
	int size;

	//Call get_board_size to read first line of file as the board size.
  get_board_size(fp, &size);
  //Check the size of the board in txt file
  if(size <= 0){
    printf("The board size is invalid\n");
    exit(1);
  }
  if(size >9){
    printf("The board size is invalid >9\n");
    exit(1);
  }
	//Dynamically allocate a 2D array for given board size.
  int **board;
  board = malloc(size*sizeof(int*));
  //Check whether board is NULL
  if(board == NULL){
    printf("Not Enough Memory\n");
    exit(1);
  }
  //Allocate each arrays in the board array
  for(int i = 0; i < size; i++){
    *(board+i) = malloc(size*sizeof(int));
    if(*(board+i) == NULL){
      printf("Not Enough Memory\n");
      exit(1);
    }
  }
  
	// Read the rest of the file line by line.
	// Tokenize each line wrt the delimiter character 
	// and store the values in your 2D array.
	char *line = NULL;
	size_t len = 0;
	char *token = NULL;
	for (int i = 0; i < size; i++) {

		if (getline(&line, &len, fp) == -1) {
			printf("Error while reading line %i of the file.\n", i+2);
			exit(1);
		}

		token = strtok(line, DELIM);
		for (int j = 0; j < size; j++) {
			// TODO: Complete the line of code below
			// to initialize your 2D array.
			 *(*(board+i)+j)= atoi(token);
			token = strtok(NULL, DELIM);
		}
	}

	// Call the function valid_board and print the appropriate
	// output depending on the function's return value.
  int checker = valid_board(board, size);
  if(checker == 0){
    printf("Invalid\n");
  }else{
    printf("Valid\n");
  }
	//Free all dynamically allocated memory.
		for (int k = 0; k < size; k++){
      free(*(board+k));
      *(board+k) = NULL;
    }
  free(board);
	//Close the file.
	if (fclose(fp) != 0) {
		printf("Error while closing the file.\n");
		exit(1);
	} 

	return 0;       
}       

