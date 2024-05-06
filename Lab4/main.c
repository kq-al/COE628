#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>

int main(int argc, char** argv) {
    char input[101];
    char parentCMD[101];
    char childCMD[101];
    int fd[2], i, pipeflag = 0, parentc = 0, childc = 0;

    printf("Your command> ");
    fgets(input, 100, stdin);
    for(i = 0; i < strlen(input)-1; i++){
	if(input[i] != '|' && pipeflag == 0){
            parentCMD[parentc] = input[i];
            parentc++;
	}else if(input[i] != '|' && pipeflag == 1){
            childCMD[childc] = input[i];
            childc++;
        }else if(input[i] == '|'){
            pipeflag = 1;
            i++;
	}
    }
	
    pipe(fd);
    pid_t pid = fork();

    if(pid == 0){
	dup2(fd[1], 1);
	close(fd[0]);
	system(childCMD);
	exit(0);
    }else{
	dup2(fd[0], 0);
	close(fd[1]);
	system(parentCMD);
    }
    return(EXIT_SUCCESS);
}