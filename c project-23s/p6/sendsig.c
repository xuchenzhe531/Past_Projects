///////////////////////////////////////////////////////////////////////////////
//
// Copyright 2020-2023 Deb Deppeler based on work by Jim Skrentny
// Posting or sharing this file is prohibited, including any changes/additions.
// Used by permission SPRING 2023, CS354-deppeler
//
///////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
// Main File:        sendsig.c
// This File:        sendsig.c
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
#include <signal.h>
#include <string.h>
/*
 * This is the main method for the user to send signal to mySigHandler
*/
int main(int argc, char *argv[]){
    //Check user input
    if (argc != 3) {
        printf("Usage: ./sendsig <signal type> <pid>\n");
        exit(1);
    }
    //Compare with the expeccted input
    if (strcmp(argv[0], "./sendsig") != 0){
        printf("Usage: ./sendsig <signal type> <pid>\n");
        exit(1);
    }
    //The following is to understand user's intention 
   int sig;
   int pid;
    if (strcmp(argv[1], "-i") == 0) {
        sig = SIGINT;
    } else if (strcmp(argv[1], "-u") == 0) {
        sig = SIGUSR1;
    } else {
        printf("Usage: ./sendsig <signal type> <pid>\n");
        exit(1);
    }
    //Use atoi to get the pid
    pid = atoi(argv[2]);
    if (strcmp(argv[1], "-i") == 0){
        //Check by kill
	    if(kill(pid, sig) < 0){
		    printf("SIGINT cannot be sent\n");
		    exit(0);
	    }
    }
    if (strcmp(argv[1], "-u") == 0){
        //Check by kill
        if(kill(pid, sig) < 0){
                printf("SIGUSR1 cannot be sent\n");
                exit(0);
        }
    }
    return 0;
}
