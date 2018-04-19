#include <stdio.h>

int insert_queue(int* queue, int n, int peak) {
    queue[n] = peak;
    n++;
    return n;
}

int extract_queue(int* queue, int n, int* peak) {
    int i;
    *peak = queue[0];
    for(i = 0; i < n; i++) {
        queue[i] = queue[i + 1];
    }
    n++;
    return n;
}

//Memory allocation for the matrix
int** alloc(int lines, int columns) {
    int** a, i;
    a = new int* [lines++];
    for(i = 0; i < lines; i++) {
        a[i] = new int[columns];
    }
    return a;
}

//Memory deallocation for the matrix
int** deallocation(int** a, int lines) {
    int i;
    for(i = 0; i < lines; i++) {
        delete a[i];
    }
    delete a;
    return a;
}

void breadth_first(int v0, int** a, int n) {
    int *queue, *m;
    int i, nr_c, k;
    queue = new int[n];
    m = new int[n];
    for(i = 0; i < n; m[i++] = 0); 
    nr_c = 0;
    nr_c = insert_queue(queue, nr_c, v0);
    m[v0] = 1;
    while(nr_c) {
        nr_c = extract_queue(queue, nr_c, &i);
        printf("\n%i", i+1);
        for(int k = 0; k < n; k++) {
            if((a[i][k] == 1)&&(m[k] == 0)) {
                nr_c = insert_queue(queue, nr_c, k);
                m[k] = 1;
            }
        }
        delete queue;
        delete m;
    }
}

int main() {
    int n, v0, **a, m, i, j, peak1, peak2;
    //Reading grapht from the user
    //Read dates are: Number of peaks, number of connections
    //Connections
    printf("Number of paeks is: ");
    scanf("%i", &n);
    a = alloc(n, n);
    for(i = 0; i < n; i++) {
        for(j = 0; j <= i; j++) {
            a[i][j] = a[i][j] = 0;
        }
    }
    printf("\n Number of connections is: ");
    scanf("%i", &m);
    for(i = 0; i < m; i++) {
        printf("Initial Peak: ");
        scanf("%i", &peak1);
        printf("Final peak: ");
        scanf("%i", &peak2);
        a[peak1 - 1][peak2 - 1] = a[peak2 - 1][peak1 - 1] = 1;
    }
    printf("\nInitial peak for BF is: ");
    scanf("%i", &v0);
    printf("\nVisiting order of peaks are: ");
    breadth_first(v0 -1, a, n);
    a = deallocation(a, n);
    getchar();
    return 0;
}