/*---------------*/
// #include <string>
// #include <iostream>
// #include<cstdio>
// #include<vector>
// #pragma warning(disable:4996)
// using namespace std;

/*-------------*/

vector<int> Kmp(string text, string key) 
{
	vector<int> ans;
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
			ans.push_back(i - key.length() + 1);
		}
	}
    delete[] kmpTable;
    return ans;
}

int main(){
	printf("Please input the text:");
    string text;
    getline(cin, text);
	printf("Please input the key:");
    string key;
    getline(cin, key);
	vector<int> ans = Kmp(text, key);
    if (ans.empty())
        printf("False");
    else{
		for (int i = 0; i < ans.size() -1; i++)
			printf("%d,", ans[i]);
		printf("%d", ans[ans.size() - 1]);
	}
}