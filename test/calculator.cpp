/*--------For Complie-------*/
// #include<iostream>
// #include<stack>
// #include<cstdio>
// #pragma warning(disable:4996)


//#define N_OPTR 7 
// using namespace std;
/*------END------*/

const int N_OPTR = 7;

const char optrs [N_OPTR] = {'+', '-', '*', '/', '(', ')','\0'}; 
const char pri[N_OPTR][N_OPTR] = { 
   /*              |-------------------- ------------| */
   /*              +    -     *     /             (     )    \0 */
	/* --  + */    '>',   '>',   '<',   '<',   '<',   '>',   '>',
	/* |   - */    '>',   '>',   '<',   '<',   '<',   '>',   '>',
	/*   * */    '>',   '>',   '>',   '>',   '<',   '>',   '>',
	/*  / */    '>',   '>',   '>',   '>',   '<',   '>',   '>',
	/*   ( */    '<',   '<',   '<',   '<',   '<',   '=',   ' ',
	/* |   ) */    ' ',   ' ',   ' ',   ' ',   ' ',   ' ',   ' ',
	/* -- \0 */    '<',   '<',   '<',   '<',   '<',   ' ',   '='
};


bool isdigit(char c) {
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
   float currentNum = 0;
   optr.push ( '\0' );
   bool isBeforeDigit = false;
   while ( !optr.empty() ) {
      if ( isdigit ( *S ) ) { 
		 currentNum = currentNum * 10 + *S - '0';
		 isBeforeDigit = true;
		 S++;
	  }
	  else 
	  {
		  if (isBeforeDigit) {
			  opnd.push(currentNum);
			  currentNum = 0;
			  isBeforeDigit = false;
		  }
		  else if (*S == '-') opnd.push(0); 

		  switch (orderBetween(optr.top(), *S)) {
		  case '<': 
			  optr.push(*S); S++; 
			  break;
		  case '=':
			  optr.pop(); S++;
			  break;
		  case '>': {
			  char op = optr.top(); optr.pop();
			  float pOpnd2 = opnd.top(); opnd.pop();
			  float pOpnd1 = opnd.top(); opnd.pop();
			  opnd.push(calcu(pOpnd1, op, pOpnd2));
		  }
				  break;
		  }
	  }
   }
   return opnd.top();
}

int main() {
	printf("Please input the expression: ");
	char expr[1024];
	scanf("%s", expr);
	printf("%g", evaluate(expr));
	return 0;
}
		
