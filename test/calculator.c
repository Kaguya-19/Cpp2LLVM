#include<stdio.h>

#define MAX_LENGTH 2000
#define N_OPTR 7 //运算符个数


char pri[N_OPTR][N_OPTR] = { //运算符优先等级 [栈顶] [当前]
   /*              |-------------------- 当 前 运 算 符 ------------| */
   /*              +       -     *       /       (     )    \0 */
	/* --  + */    '>',   '>',   '<',   '<',   '<',   '>',   '>',
	/* |   - */    '>',   '>',   '<',   '<',   '<',   '>',   '>',
	/* 栈  * */    '>',   '>',   '>',   '>',   '<',   '>',   '>',
	/* 顶  / */    '>',   '>',   '>',   '>',   '<',   '>',   '>',
	/* 符  ( */    '<',   '<',   '<',   '<',   '<',   '=',   ' ',
	/* |   ) */    ' ',   ' ',   ' ',   ' ',   ' ',   ' ',   ' ',//不会在栈顶的
	/* -- \0 */    '<',   '<',   '<',   '<',   '<',   ' ',   '='
};

char orderBetween(char a, char b) {
	return pri[charToInt(a)][charToInt(b)];
}
int charToInt(char c){
	if (c == '+') return 0;
	if (c == '-') return 1;
	if (c == '*') return 2;
	if (c == '/') return 3;
	if (c == '(') return 4;
	if (c == ')') return 5;
	if (c == '\0') return 6;
}

float calcu(float a, char theta, float b) {
	if (theta == '+') return a + b;
	if (theta == '-') return a - b;
	if (theta == '*') return a * b;
	if (theta == '/') return a / b;
}


// a calculator that can calculate the expression with + - * / ( ) and the number is less than 1000W
// the expression must be in the form of prefix expression
// for example: 1 + 2 * 3 - 4 / 5
float cal(char* s){
    float opnd[MAX_LENGTH];
    char optr[MAX_LENGTH];
    int i = 0;
    int opndTop = -1, optrTop = -1;
    optr[++optrTop] = '\0';
    int isBeforeDigit = 0;
    float currentNum = 0;
    for(;optrTop>=0;){
        if(s[i] >= '0' && s[i] <= '9')
        {
            currentNum = currentNum * 10 + s[i] - '0';
            isBeforeDigit = 1;
            i = i + 1;
        }
         else //若当前字符为运算符，则
	  {
		  if (isBeforeDigit != 0) {
			  opnd[++opndTop] = currentNum;
			  currentNum = 0;
			  isBeforeDigit = 0;
		  }
		  else if ( s[i] == '-') {
              opnd[++opndTop] = 0;
          }
		  char order = orderBetween(optr[optrTop], s[i]);
		  if(order == '<') //栈顶运算符优先级更低时
		  {
			  optr[++optrTop] = s[i]; i = i+1; //当前运算符进栈
		  }
		  else if(order == '>') //栈顶运算符优先级更高时
		  {
			  char op = optr[optrTop]; optrTop = optrTop  - 1;
			  float pOpnd2 = opnd[opndTop]; opndTop = opndTop  - 1;
			  float pOpnd1 = opnd[opndTop]; opndTop = opndTop  - 1;
			  opnd[++opndTop] = calcu(pOpnd1, op, pOpnd2);
		  }
		  else if(order == '=') //栈顶运算符优先级相等时
		  {
			  optrTop = optrTop  - 1; i++;
		  }
	  }
   }
   return opnd[opndTop];
}

int main()
{
    char input[MAX_LENGTH];
    printf("please input the expression: ");
    gets(input);
    printf("%g",cal(input));
    return 0;
}