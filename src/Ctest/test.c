#include <stdlib.h> 
#include <stdio.h> 
#define size 5

int main()
{
    char *arr[size] = {"banana", "apple", "pear"};
    int key, i, index = -1;

    for(i = 0; i < size; i++)
    {
        if(i == 2)
        {
            index = i;
            break;
        }
    }

        for(i = index; i < size - 1; i++)
            arr[i] = arr[i+1];

        for(i = 0; i < size - 1; i++)
            printf("%d %s",i, arr[i]);

    return 0;

}
