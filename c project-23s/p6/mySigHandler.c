///////////////////////////////////////////////////////////////////////////////
//
// Copyright 2020-2023 Deb Deppeler based on work by Jim Skrentny
// Posting or sharing this file is prohibited, including any changes/additions.
// Used by permission SPRING 2023, CS354-deppeler
//
///////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
// Main File:        mySigHandler.c
// This File:        mySigHandler.c
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
#include <unistd.h>
#include <time.h>
#include <string.h>

int seconds = 4; //print every 4 second 
int usr_counter = 0; //count number of handling
/*
 * This method is to deal with SIGALRM and accept an integer as the input
*/
void alarm_handler(int signum) {
    time_t curr_time;
    //Check the curr_time 
    if (time(&curr_time) == -1){
	printf("Error:alarm_handler() : current time is wrong.\n");
	exit(0);
    }
    printf("PID: %d CURRENT TIME: %s", getpid(), ctime(&curr_time));
    //Check the alarm
    if(alarm(seconds)!= 0){
	printf("Error:alarm_handler() : alarm is wrong.\n");
	exit(0);
    }
    //Set the alarm
    alarm(seconds);
}
/*
 * This method is to deal with SIGUSR1 and accept an integer as the input
*/
void usr_handler(int signum){
    printf("SIGUSR1 handled and counted!\n");
    usr_counter++;
} 
/*
 * This method is to deal with SIGINT and accept an integer as the input
*/
void sigint_handler(int signum){
    printf("\nSIGINT handled.\n");
    printf("SIGUSR1 was handled %d times. Exiting now.\n", usr_counter);
    exit(0);
}
/*
 * This method is the main function to declare sigaction
*/
int main() {
    //declare sigaction
    struct sigaction act;
    memset (&act, 0, sizeof(act));
    act.sa_handler= alarm_handler;
    //Check sigaction
    if (sigaction(SIGALRM, &act, NULL) < 0) {
        printf("Error:SIGALRM: cannot be handled");
        exit(0);
    }
    //declare sigaction
    struct sigaction sigusr_one;
    memset (&sigusr_one, 0, sizeof(sigusr_one));
    sigusr_one.sa_handler = usr_handler;
    //Check sigaction
    if (sigaction(SIGUSR1, &sigusr_one, NULL) < 0) {
        printf("Error:SIGUSR1: cannot be handled");
        exit(0);
    }
    //declare sigaction
    struct sigaction sig_int;
    memset (&sig_int, 0, sizeof(sig_int)); 
    sig_int.sa_handler = sigint_handler;
    //Check sigaction
    if (sigaction(SIGINT, &sig_int, NULL) < 0) {
        printf("Error:SIGINT: cannot be handled");
        exit(0);
    }
    printf("PID and time print every 4 seconds.\n");
    printf("Type Ctrl-C to end the program.\n");
    alarm(seconds);
    //Infinite loop
    while(1) {
    }
    return 0;
}
