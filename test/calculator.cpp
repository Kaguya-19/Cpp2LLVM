#include<iostream>
#include<stack>
using namespace std;

#define N_OPTR 7 //运算符数

const char optrs [N_OPTR] = {'+', '-', '*', '/', '(', ')','\0'}; //运算符集合
const char pri[N_OPTR][N_OPTR] = { //运算符优先等级 [栈顶] [当前]
   /*              |-------------------- 当 前 运 算 符 ------------| */
   /*              +    -     *     /             (     )    \0 */
	/* --  + */    '>',   '>',   '<',   '<',   '<',   '>',   '>',
	/* |   - */    '>',   '>',   '<',   '<',   '<',   '>',   '>',
	/* 栈  * */    '>',   '>',   '>',   '>',   '<',   '>',   '>',
	/* 顶  / */    '>',   '>',   '>',   '>',   '<',   '>',   '>',
	/* 符  ( */    '<',   '<',   '<',   '<',   '<',   '=',   ' ',
	/* |   ) */    ' ',   ' ',   ' ',   ' ',   ' ',   ' ',   ' ',//不会在栈顶的
	/* -- \0 */    '<',   '<',   '<',   '<',   '<',   ' ',   '='
};
//以上来自数据结构课件

bool isDigit(char c) {
	return c >= '0' && c <= '9';
}

char orderBetween(char a, char b) {
	return pri[find(optrs, optrs + N_OPTR, a) - optrs][find(optrs, optrs + N_OPTR, b) - optrs];
}

float calcu(float a, char theta, float b) {
	switch (theta)
	{
	case '+':
		return a + b;
	case '-':
		return a - b;
	case '*':
		return a * b;
	case '/':
		return a / b;
	default:
		return 0;
	}
}

float evaluate ( char* S ) { 
   stack<float> opnd; stack<char> optr; 
   char* expr = S;
   optr.push ( '\0' ); 
   while ( !optr.empty() ) {
      if ( isdigit ( *S ) ) { 
         opnd.push ( float ( *S++ - '0' ) );
      } else //若当前字符为运算符，则
         switch ( orderBetween ( optr.top(), *S ) ) { 
            case '<': //栈顶运算符优先级更低时
               optr.push ( *S ); S++; //计算推迟，当前运算符进栈
               break;
            case '=': 
               optr.pop(); S++; 
               break;
 	    	case '>': { 
               	char op = optr.top(); optr.pop(); 
                float pOpnd2 = opnd.top(); opnd.pop(); 
				float pOpnd1 = opnd.top(); opnd.pop();
                opnd.push ( calcu ( pOpnd1, op, pOpnd2 ) );
               }
               break;
			}
   }
   return opnd.top();
}

int main() {
	cout << "Please input the expression: ";
	char expr[1024];
	cin >> expr;
	cout << evaluate(expr) << endl;
	return 0;
}
		
