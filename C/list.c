#include <stdio.h>
#include <stdlib.h>

typedef struct node {
	char info;
	struct node *leg;
} list, *lista;



int ins_head(lista *cap, char inf) {
	lista nou;
	nou = (lista)malloc(sizeof(list));	
	
	if(nou != NULL) {
		if(*cap != NULL) {
			nou -> info = inf;
			nou -> leg = *cap;
			*cap  = nou;
		} else return 0;		
	
	} else return 0;

	return 1;
}

void display(lista *cap) {
	while (*cap != NULL) {
		printf ("%c", (*cap) -> info);
		*cap = (*cap) -> leg; 	
	}
}

int main() {
	lista *cap = NULL;

	for(int i = 0; i < 6; i++) {
		printf("Add value for the element %d in the list: \n", i);
		
		char crt;
		scanf("%c", &crt);
		ins_head (cap, crt);
	}

	display (cap);
	
	return 0;
}
