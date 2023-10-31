///////////////////////////////////////////////////////////////////////////////
// Copyright 2020 Jim Skrentny
// Posting or sharing this file is prohibited, including any changes/additions.
// Used by permission, CS 354 Spring 2022, Deb Deppeler
////////////////////////////////////////////////////////////////////////////////
   
////////////////////////////////////////////////////////////////////////////////
// Main File:        myMagicSquare.c
// This File:        myMagicSquare.c
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

// Structure that represents a magic square
typedef struct {
    int size;           // dimension of the square
    int **magic_square; // pointer to heap allocated magic square
} MagicSquare;

/* Prompts the user for the magic square's size, reads it,
 * checks if it's an odd number >= 3 (if not display the required
 * error message and exit), and returns the valid number.
 * Pre-conditions: Describe conditions required for function to work as advertised
 * return the size of the intended square
 */
int getSize() {
    printf("Enter magic square's size (odd integer >=3)\n");
    int input;
    //Exit when the input is not correct
    if(scanf("%i", &input) != 1){
      printf("Please Enter magic square's size (odd integer >=3)\n");
      exit(1);
    }else{
      //Exit when the input is not correct
      if(input <= 0){
        printf("Magic square size must be positive.\n");
        exit(1);        
        //Exit when the input is not correct
      }else if(input%2 == 0){
        printf("Magic square size must be odd.\n");
        exit(1);
      }
    }
    return input;   
} 
/* Makes a magic square of size n using the 
 *Siamese magic square algorithm or alternate from assignment 
 * or another valid alorithm that produces a magic square.
 * Pre-conditions: the user's input for size is correct
 *
 * n is the number of rows and columns
 *
 * return the pointer to the struct
 */
MagicSquare *generateMagicSquare(int n) {
  //Create a pointer to MagicSquare
  MagicSquare * magic;
  magic = malloc(sizeof(MagicSquare));
  //Exit if malloc is not successful
  if(magic == NULL){
    printf("Not Enough Memory\n");
    exit(1);
  }
  magic->magic_square = malloc(n*sizeof(int*));
  (*magic).size = n;
  //Exit if malloc is not successful
  if(magic->magic_square == NULL){
    printf("Not Enough Memory\n");
    exit(1);
  }
    //Allocate each arrays in the board array
    for(int i = 0; i < n; i++){
      *(magic->magic_square+i) = malloc(n*sizeof(int));
      //Exit if malloc is not successful
      if(*(magic->magic_square+i) == NULL){
        printf("Not Enough Memory\n");
        exit(1);
    }
  }
  //Add each element of the 2D array to 0
  for(int i = 0; i < n; i++){
    for(int j = 0; j < n; j++){
        *(*(magic->magic_square+i)+j) = 0;
    }
  }
  //Set the first 1 to the middle of the first row
  int first_column = n/2;
  int first_row = 0;
  *(*magic->magic_square+first_column) = 1;
  //Loop through n^2 times
  for(int i = 1; i < n*n; i++){
    //When move up will out of bounce
    if((first_row -1) < 0){
      int current_row = n-1;
      //When move up will out of bounce, but move right not out of bounce
      if(first_column + 1 < n){
        if(*(*(magic->magic_square+current_row)+(first_column+1)) == 0){
          *(*(magic->magic_square+current_row)+(first_column+1)) = i+1;
          first_row = current_row;
          first_column = first_column+1;
          //When move up will out of bounce, but move right out of bounce, then just move down
        }else{
          *(*(magic->magic_square+(first_row+1))+(first_column)) = i+1;
          first_row++;
        }
        //When move up will out of bounce, but move right out of bounce
      }else{
        int current_column = 0;
        //If the column already be occupied
        if(*(*(magic->magic_square+(current_row))+(current_column)) == 0){
          *(*(magic->magic_square+(current_row))+(current_column)) = i+1;
          first_row = current_row;
          first_column = current_column;
        }else{
          //If the column not be occupied yet
          *(*(magic->magic_square+(first_row+1))+(first_column)) = i+1;
          first_row++;
        }
      }
      //When move up will not out of bounce
    }else{
      int current_row = first_row -1;
      //When move up will not out of bounce, and move right out of bounce
      if(first_column + 1 >= n){
        int current_column = 0;
        //If the column already be occupied
        if(*(*(magic->magic_square+current_row)+(current_column)) == 0){
          *(*(magic->magic_square+current_row)+(current_column)) = i+1;
          first_column = current_column;
          first_row--;
          //If the column not be occupied yet
        }else{
          *(*(magic->magic_square+(first_row+1))+(first_column)) = i+1;
          first_row++;
        }
         //When move up will not out of bounce, and move right not out of bounce
      }else{
        //If the column already be occupied
        int current_column = first_column +1;
        if(*(*(magic->magic_square+current_row)+(current_column)) == 0){
          *(*(magic->magic_square+current_row)+(current_column)) = i+1;
          first_row = current_row;
          first_column = current_column;
        }else{
          //If the column not be occupied yet
          *(*(magic->magic_square+(first_row+1))+(first_column)) = i+1;
          first_row++;
        }
      }
    }
  }
  return magic;
} 
/* Opens a new file (or overwrites the existing file)
 * and writes the square in the specified format.
 *
 * Pre-conditions: the square has been created and filename has been stored
 * magic_square the magic square to write to a file
 * filename the name of the output file
 */
void fileOutputMagicSquare(MagicSquare *magic_square, char *filename) {
  // Open the file and check if it opened successfully.
  FILE *fp = fopen(filename, "w");
	if (fp == NULL) {
		printf("Can't open file for reading.\n");
		exit(1);
	}
  //Print the file content
  fprintf(fp, "%i\n", magic_square->size);
  for(int i = 0; i < magic_square->size; i++){
    for(int j = 0; j < magic_square->size; j++){
      fprintf(fp, "%i", *(*(magic_square->magic_square+i)+j));
      if(j != magic_square->size-1){
        fputs(",", fp);
      }
    }
    fputs("\n", fp);
  }
  //Close the file.
	if (fclose(fp) != 0) {
		printf("Error while closing the file.\n");
		exit(1);
	} 
}
/*Generates a magic square of the user specified size and
 * outputs the square to the output filename.
 *
 * Pre-conditions: the user has ran this program and enter the file name
 * argc: number of inputs
 * argv: input array
 * return 0 if the program runs correctly
 */

/* TODO:
 * Generates a magic square of the user specified size and
 * outputs the square to the output filename.
 * 
 * Add description of required CLAs here
 */
int main(int argc, char **argv) {
	 // Check if number of command-line arguments is correct.
    char *filename;
    if(argc == 1){
      printf("Usage: ./myMagicSquare <output_filename>\n");
      exit(1);
    }else if(argc==2){
      filename = *(argv+1);
    }else{
      printf("Invalid number of arguments\n");
      exit(1); 
    }

    // TODO: Get magic square's size from user
    int input = getSize();

    // TODO: Generate the magic square by correctly interpreting 
    //       the algorithm(s) in the write-up or by writing or your own.  
    //       You must confirm that your program produces a 
    //       Magic Sqare as described in the linked Wikipedia page.
    MagicSquare *magic = generateMagicSquare(input);

    // TODO: Output the magic square
    fileOutputMagicSquare(magic, filename);
    for(int i = 0; i < input; i++){
      free(*(magic->magic_square+i));
    }
    free(magic->magic_square);
    free(magic);
    return 0;
} 

// S23

