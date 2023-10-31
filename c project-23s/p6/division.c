///////////////////////////////////////////////////////////////////////////////
//
// Copyright 2020-2023 Deb Deppeler based on work by Jim Skrentny
// Posting or sharing this file is prohibited, including any changes/additions.
// Used by permission SPRING 2023, CS354-deppeler
//
///////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
// Main File:        division.c
// This File:        division.c
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
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <time.h>
#include <string.h>

int operation_count = 0; // constant to count the number of divison
/*
 * This method is to deal with SIGINT and will accept an integer as an input
 * After it finishes, the corresponding message will be sent and exit
 */
void sigint_handler(int signum){
    printf("\n");
    printf("Total number of operations completed successfully: %i\n", operation_count);
    printf("The program will be terminated.\n");
    exit(0);
}
/*
 * This method is to deal with SIGFPE and will accept an integer as an input
 * After it finishes, the corresponding message will be sent and exit
 */
void sigfpe_handler(int signum){
    printf("Error: a division by 0 operation was attempted.\n");
    printf("Total number of operations completed successfully: %i\n", operation_count);
    printf("The program will be terminated.\n");
    exit(0);
}
/*
 * This is the main method to declare sigaction and to process the user input to do
 * division and print out the corresponding output
 */
int main(int argc, char **argv){
    //Create a sigaction for SIGINT
    struct sigaction act_sigint;
    memset(&act_sigint, 0, sizeof(act_sigint));
    act_sigint.sa_handler = sigint_handler;
    //Check the sigaction for SIGINT
    if (sigaction(SIGINT, &act_sigint, NULL) < 0)
    {
        printf("Error:SIGINT: cannot be handled");
        exit(0);
    }
    //Create a sigaction for SIGFPE
    struct sigaction act_sigfpe;
    memset(&act_sigfpe, 0, sizeof(act_sigfpe));
    act_sigfpe.sa_handler = sigfpe_handler;
    //Check the sigaction for SIGINT
    if (sigaction(SIGFPE, &act_sigfpe, NULL) < 0)
    {
        printf("Error:SIGINT: cannot be handled");
        exit(0);
    }
    //The while loop is to process user's input and print out the corresponding message
    while (1)
    {
        char buffer[100]; //a buffer of 100 bytes
        int remainder;
        int quotient;
        int dividend;
        int divisor;
        printf("Enter first integer: ");
        fgets(buffer, 100, stdin); //use fgets() to read each line of input
        dividend = atoi(buffer); //use atoi() to translate that C string to an integer
        printf("Enter second integer: ");
        fgets(buffer, 100, stdin); //use fgets() to read each line of input
        divisor = atoi(buffer); //use atoi() to translate that C string to an integer
        quotient = dividend / divisor;
        remainder = dividend % divisor;
        printf("%i / %i is %i with a remainder of %i\n", dividend, divisor, quotient, remainder);
        operation_count++; //increment the constant
    }
    return 0;
}
