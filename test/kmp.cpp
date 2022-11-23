#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

int Kmp(string text, string key) 
{
	int k = -1; 
	int* kmpTable = new int[key.length()];
	kmpTable[0] = -1;
	for (int i = 1; i < key.length(); i++) 
	{
		while (k>=0 && key[i] != key[k + 1]) 
			k = kmpTable[k];
		if (key[i] == key[k + 1]) 
			k++;
		kmpTable[i] = k;
	}
	k = -1;
	for (int i = 0; i < text.length() ; i++) 
	{
		while (k>=0 && text[i] != key[k + 1]) 
			k = kmpTable[k];
		if (text[i] == key[k + 1]) 
			k++;
		if (k == key.length() - 1)  
		{
            delete[] kmpTable;
			return i - key.length() + 1;
		}
	}
    delete[] kmpTable;
    return -1;
}

int main(){
    cout << "Please input the text:" << endl;
    string text;
    cin >> text;
    cout << "Please input the key:" << endl;
    string key;
    cin >> key;
    if (Kmp(text, key) == -1)
        cout << "Not found!" << endl;
    else
        cout << "Found at " << Kmp(text, key) << endl;
}