#include <stdlib.h>
#include  <string.h>
#define MAX_LENGTH 1024

char* kmp(char* text,char* key){
    // next数组
	int next[MAX_LENGTH];
	next[0] = -1;

	int len_text, len_key;
    len_text = strlen(text);
	len_key = strlen(key);

	for (int i = 1,j = -1; i < len_key;  i = i + 1) 
	{
		for (; j >= 0 && key[i] != key[j+1]; j = next[j]);
		if (key[i] == key[j+1]) 
		{
            j = j + 1;
        }
		next[i] = j;
	}


	int matched = 0;
	for (int i = 0, j = -1; i < len_text; i = i + 1) 
	{
		for (; j >= 0 && text[i] != key[j+1]; j = next[j]);
		if (text[i] == key[j+1]) 
		{
            j = j + 1;
        }
		if (j + 1 == len_key) 
		{
			matched = 1;
			printf("%d\n", i - j);
            return i-j;
			j = next[j];
		}
	}
    return matched;
}

int main() {
	char text[MAX_LENGTH];
	char key[MAX_LENGTH];
    char ans[MAX_LENGTH];


	printf("Please input the text:");
	gets(text);
	printf("Please input the key:");
	gets(key);

    int matched = kmp(text,key);
    if (matched == 0){
		printf("False\n");
    }

	return 0;
}