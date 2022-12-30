
int printf(const char *format, ...);
int memset(unsigned int*s, int c, int n);
int memcpy(unsigned int*dest,unsigned int *src, int n);
void* malloc(int size);
void free(void *ptr);

void swap(unsigned int *a, int i, int j)
{
	unsigned int b = a[i];
	a[i] = a[j];
	a[j] = b;
}

void insertSort(unsigned int *a,int n)
{
	int j = 0;
	unsigned int k;
	for (int i = 1; i < n; i++)
	{
		j = i-1;
		k = a[i];
		while (j >= 0 && a[j] > k)
		{
			a[j + 1] = a[j];
			j--;
		}
		a[j+1] = k;
	}
}

void quickSort(unsigned int *a, int l, int r) {
	if (l >= r)
		return;
	unsigned int mid = a[r];
	int l1 = l, r1 = r - 1;
	while (l1 < r1) 
	{
		while (a[l1] < mid && l1 < r1)
			l1++;
		while (a[r1] >= mid && l1 < r1)
			r1--;
		swap(a, l1, r1);
	}
	if (a[l1] >= a[r])
		swap(a, l1, r);
	else
		l1++;
	quickSort(a, l, l1 - 1);
	quickSort(a, l1 + 1, r);
}

void shellSort(unsigned int *a, int n) {
	int i, j,gap;
	unsigned int k;
	for (gap = n >> 1; gap > 0; gap >>= 1)
	{
		for (i = gap; i < n; i++) 
		{
			k = a[i];
			for (j = i - gap; j >= 0 && a[j] > k; j -= gap)
				a[j + gap] = a[j];
			a[j + gap] = k;
		}
	}	
}

void merge(unsigned int *a, int l, int m, int r) {
    int i = l, j = m + 1, k = 0;
    unsigned int *b = malloc((r - l + 1) * 4);
    while (i <= m && j <= r) {
        if (a[i] < a[j])
            b[k++] = a[i++];
        else
            b[k++] = a[j++];
    }
    while (i <= m)
        b[k++] = a[i++];
    while (j <= r)
        b[k++] = a[j++];
    for (i = l, k = 0; i <= r; i++, k++)
        a[i] = b[k];
	free(b);
}

void mergeSort(unsigned int *a, int l, int r) {
    if (l >= r)
        return;
    int m = (l + r) >> 1;
    mergeSort(a, l, m);
    mergeSort(a, m + 1, r);
    merge(a, l, m, r);
}


void radixSort(unsigned int *a, int n) {
	int i;
	unsigned int exp = 1;
	unsigned int m = a[0];
	for (i = 1; i < n; i++)
	{
		if (a[i] > m) 
			m = a[i];
	}
	while (m / exp > 1) 
	{
		unsigned int b[100];
		unsigned int bucket[10];
		memset(bucket, 0, 10 * 4 );
		for (i = 0; i < n; i++)
			bucket[(a[i] / exp) % 10]++;
		for (i = 1; i < 10; i++) 
			bucket[i] += bucket[i - 1];
		for (i = n - 1; i >= 0; i--) 
			b[--bucket[(a[i] / exp) % 10]] = a[i];
		memcpy(a, b, n * 4);
		exp *= 10;
	}
}

void print(unsigned int *a,int n)
{
	for (int i = 0; i < n; i++)
		printf("%u ", a[i]);
	printf("\n");
}

int main()
{
    unsigned int a[10] = { 1, 3, 2, 5, 4, 7, 6, 9, 8, 0 };
    unsigned int b[10] = { 1, 3, 2, 5, 4, 7, 6, 9, 8, 0 };
    unsigned int c[10] = { 1, 3, 2, 5, 4, 7, 6, 9, 8, 0 };
    unsigned int d[10] = { 1, 3, 2, 5, 4, 7, 6, 9, 8, 0 };
    unsigned int e[10] = { 1, 3, 2, 5, 4, 7, 6, 9, 8, 0 };
    int n = 10;
    printf("InsertSort:\n");
    print(a, n);
    insertSort(a, n);
    print(a, n);

    printf("MergeSort:\n");
    print(d, n);
    mergeSort(d, 0, n - 1);
    print(d, n);

    printf("QuickSort:\n");
    print(b, n);
    quickSort(b, 0, n - 1);
    print(b, n);
    printf("ShellSort:\n");
    print(c, n);
    shellSort(c, n);
    print(c, n);

    printf("RadixSort:\n");
    print(e, n);
    radixSort(e, n);
    print(e, n);
    

    return 0;
}