#include <stdio.h>
#include <string.h>

int main(){

	FILE *file;
	char string[25];

	file = fopen("fputs_file.txt", "w");

	if(file == NULL){
		printf("Unable to open the file\n");
	}
	else{
		printf("Enter the string you want to save\n");
		gets(string);
		fputs(string, file);
		printf("Data was written successfully\n");
		fclose(file);
	}

	return 0;
}
