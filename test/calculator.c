#include<stdio.h>

char orderBetween(char a, char b) {
	int pri[49];
	pri[0] = 0; pri[1] = 0; pri[2] = 1; pri[3] = 1; pri[4] = 1; pri[5] = 0; pri[6] = 0;
	pri[7] = 0; pri[8] = 0; pri[9] = 1; pri[10] = 1; pri[11] = 1; pri[12] = 0; pri[13] = 0;
	pri[14] = 0; pri[15] = 0; pri[16] = 0; pri[17] = 0; pri[18] = 1; pri[19] = 0; pri[20] = 0;
	pri[14] = 0; pri[15] = 0; pri[16] = 0; pri[17] = 0; pri[18] = 1; pri[19] = 0; pri[20] = 0;
	pri[21] = 1; pri[22] = 1; pri[23] = 1; pri[24] = 1; pri[25] = 1; pri[26] = 2; pri[27] = 3;
	pri[28] = 3; pri[29] = 3; pri[30] = 3; pri[31] = 3; pri[32] = 3; pri[33] = 3; pri[34] = 3;
	pri[35] = 1; pri[36] = 1; pri[37] = 1; pri[38] = 1; pri[39] = 1; pri[40] = 3; pri[41] = 2;
	int a_int = charToInt(a);
	int b_int = charToInt(b);
	int k = a_int * 7 + b_int;
	k = pri[k];
	return k;
}

int charToInt(char c){
	int res;
	if (c == '+')  {res= 0;}
	else if (c == '-')  {res= 1;}
	else if (c == '*')  {res= 2;}
	else if (c == '/')  {res= 3;}
	else if (c == '(')  {res= 4;}
	else if (c == ')')  {res= 5;}
	else if (c == '\0') {res= 6;}
	return res;
}

float calcu(float a, char theta, float b) {
	int res;
	if (theta == '+') {res = a + b;}
	else if (theta == '-') {res = a - b;}
	else if (theta == '*') {res = a * b;}
	else if (theta == '/') {res = a / b;}
	return res;
}

int main()
{
    char s[2000];
    printf("please input the expression: ");
    gets(s);


//    /*              +       -     *       /       (     )    \0 */
// 	/* --  + */    0,   0,   1,   1,   1,   0,   0,
// 	/* |   - */    0,   0,   1,   1,   1,   0,   0,
// 	/*   * */    0,   0,   0,   0,   1,   0,   0,
// 	/*  / */    0,   0,   0,   0,   1,   0,   0,
// 	/*   ( */    1,   1,   1,   1,   1,   2,   3,
// 	/* |   ) */    3,   3,   3,   3,   3,   3,   3,
// 	/* -- \0 */    1,   1,   1,   1,   1,   3,   2



	float opnd[2000];
    char optr[2000];
    int i = 0;
    int opndTop = -1, optrTop = -1;
	optrTop = optrTop + 1;optr[optrTop] = '\0';
    int isBeforeDigit = 0;
    float currentNum = 0;
    for(;optrTop>=0;){
        if(s[i] >= '0' && s[i] <= '9')
        {
            currentNum = currentNum * 10 + s[i] - '0';
            isBeforeDigit = 1;
            i = i + 1;
        }
         else 
	  {
		  if (isBeforeDigit != 0) {
			  opndTop = opndTop+1; opnd[opndTop] = currentNum;
			  currentNum = 0;
			  isBeforeDigit = 0;
		  }
		  else if ( s[i] == '-') {
              opndTop = opndTop+1;opnd[opndTop] = 0;
          }
		  char order;
		  int ord1 = optr[optrTop];
		  int ord2 = s[i];
		  order = orderBetween(ord1, ord2);
		  if(order == 1) 
		  {
			  optrTop=optrTop+1;optr[optrTop] = s[i]; i = i+1; 
		  }
		  else if(order == 0) 
		  {
			  char op = optr[optrTop]; optrTop = optrTop  - 1;
			  float pOpnd2 = opnd[opndTop]; opndTop = opndTop  - 1;
			  float pOpnd1 = opnd[opndTop]; opndTop = opndTop  - 1;
			  opndTop=opndTop+1;opnd[opndTop] = calcu(pOpnd1, op, pOpnd2);
		  }
		  else if(order == 2) 
		  {
			  optrTop = optrTop  - 1; i = i+1;
		  }
	  }
    }
   	int res = opnd[opndTop];
    printf("%g",res);
    return 0;
}