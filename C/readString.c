#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int get_printName() {
	char name[30] = "hkhgjh";
	printf("Enter name: ");
	gets(name);
	printf("Name is: ");
	puts(name);
	int length = strlen(name);
	printf("Name length is: %d", length);
	return 0;
}

int get_printName2() {
		char name[30] , ch;
	int i = 0;
	printf("Enter name: ");
	while(ch != '\n') {
		ch = getchar();
		name[i] = ch;
		i++;
	}
	name[i] = '\0';
	printf("Name: %s", name);
	return 0;
}

int main() {
	get_printName();
	get_printName2();
	return 0;
}