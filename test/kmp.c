#include <stdio.h>
int main() {
	char text[1024];
	char key[1024];
	int kmpTable[1024];
	int textLen;
	int keyLen;
	int i,j;
	int matched = 0;
	int k;

	printf("Please input the text:");
	gets(text);
	printf("Please input the key:");
	gets(key);

    
	kmpTable[0] = -1;

    textLen = strlen(text);
	keyLen = strlen(key);

	for (i = 1,j = -1; i < keyLen;  i = i + 1) 
	{
		for (; j >= 0 && key[i] != key[j+1]; j = kmpTable[j]);
		if (key[i] == key[j+1]) 
		{
            j = j + 1;
        }
		kmpTable[i] = j;
	}


	for (i = 0, j = -1; i < textLen; i = i + 1) 
	{
		for (; j >= 0 && text[i] != key[j+1]; j = kmpTable[j]);
		if (text[i] == key[j+1]) 
		{
            j = j + 1;
        }
		if (j + 1 == keyLen) 
		{
			matched = 1;
			k = i -j ;
			printf("%d ", k);
		}
	}
	if (matched == 0){
	printf("False\n");
    }
	return 0;
}