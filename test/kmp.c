#include <stdio.h>
#define MAX_LENGTH 1024

int kmp(char* text,char* key){
	int kmpTable[MAX_LENGTH];
	kmpTable[0] = -1;

	int textLen;
	int keyLen;
    textLen = strlen(text);
	keyLen = strlen(key);

	for (int i = 1,j = -1; i < keyLen;  i = i + 1) 
	{
		for (; j >= 0 && key[i] != key[j+1]; j = kmpTable[j]);
		if (key[i] == key[j+1]) 
		{
            j = j + 1;
        }
		kmpTable[i] = j;
	}

	int matched = 0;
	for (int i = 0, j = -1; i < textLen; i = i + 1) 
	{
		for (; j >= 0 && text[i] != key[j+1]; j = kmpTable[j]);
		if (text[i] == key[j+1]) 
		{
            j = j + 1;
        }
		if (j + 1 == keyLen) 
		{
			matched = 1;
			printf("%d ", i - j);
		}
	}
	if (matched == 0){
	printf("False\n");
    }
    return matched;
}

int main() {
	char text[MAX_LENGTH];
	char key[MAX_LENGTH];

	printf("Please input the text:");
	gets(text);
	printf("Please input the key:");
	gets(key);

    kmp(text,key);
	return 0;
}