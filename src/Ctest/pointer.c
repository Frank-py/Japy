#include <stdlib.h>
#include <stdio.h>
#include <string.h>
int main(int argc, char const *argv[])
{
    char *array = "Hello";
    for (int i = 0; i < strlen(array); i++){
        printf("%c", array[i]);
    }
    return 0;
}
