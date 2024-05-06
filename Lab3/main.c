#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>



int main(int argc, char** argv) {
    int ch, i, argcount, flag, forkID;
    char array[101], *argpoint[101];
    
    argpoint[0] = array;
    flag = 0; 
    i=0;
    argcount = 0; 
            
    do {
    
        printf("Your command>");
        while ( (ch = getchar()) != EOF && (ch != '\n') && i<100){
            if (ch == ' '){
                array[i] = '\0'; 
                argpoint[argcount]=&array[i+1];
            }
            else{
                array[i] = ch; 
                argpoint[argcount] = array[i];
            }
            i++; 
            argcount++; 
        }
        
        array[i] = '\0';
        
        if (array [i-1] == '&'){
            flag = 1; 
        }
        else{
            flag = 0; 
        }
        
        forkID = fork(); 
        if (forkID < 0) {
            printf("fork error"); 
            return EXIT_FAILURE; 
        }
        else if (forkID == 0){
            execvp(argpoint, argpoint);
        }
        else if (flag != 1) {
            wait(NULL);
        }
        
        i= 0; 
        argcount = 0; 
        
    }while (ch != EOF);
    return EXIT_SUCCESS;
}