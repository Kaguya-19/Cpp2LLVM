int gets(char *s);
int printf(const char *format,...);
int strlen(const char * s);
int memset(char *str, int c, int n);
int atoi(const char * str);


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

char orderBetweenOp(char op1, char op2) {
    char pri[49] = {
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

    int x = charToInt(op1);
    int y = charToInt(op2);

    return pri[x*7+y];
}

int calc(int a, int b, char op) {
     if (op == '+')
         return a + b;
     if (op == '-')
         return a - b;
     if (op == '*')
         return a * b;
     if (op == '/')
         return a / b;
     return 0;
}


struct opndStack {
    int *data;
    int top;
};
struct optrStack {
    char *data;
    int top;
};

int popNum(struct opndStack * stack) {
    if (stack->top >= 0) {
        stack->top -= 1;
        return stack->data[stack->top + 1];
    }
    return 0;
}

void pushNum(struct opndStack * stack, int num) {
    stack->data[++stack->top] = num;
}

char popOp(struct optrStack * stack) {
    if (stack->top >= 1)
        return stack->data[stack->top--];
    return '\0';
}

void pushOp(struct optrStack * stack, char op) {
    stack->top++;
    stack->data[stack->top] = op;
}

char topOp(struct optrStack * stack) {
    if (stack->top < 0)
        return '0';
    return stack->data[stack->top];
}


int main () {
    char s[512];
    printf("please input the expression: ");
    gets(s);
    int expr_len = strlen(s);

    struct opndStack opnd;
    int opndData[512];
    opnd.top = -1;
    opnd.data = opndData;

    struct optrStack optr;
    char optrData[512];
    optr.top = -1;
    optr.data = optrData;

    char currentNum[15];
    memset(currentNum, 0, 15);

    pushOp(&optr, '\0');

    for (int i = 0, k = 0; i <= expr_len;) {
        if (s[i] >= '0' && s[i] <= '9') {
            currentNum[k++] = s[i++];
            continue;
        }

        if (k != 0) {
            pushNum(&opnd, atoi(currentNum));
            memset(currentNum, 0, 15);
            k = 0;
        }

        char result = orderBetweenOp(topOp(&optr), s[i]);
        // switch (result)
        // {
        // case '<':
        //     pushOp(&optr, s[i++]);
        //     break;
        // case '=':
        //     popOp(&optr);
        //     i++;
        //     break;
        // case '>':
        //     int a = popNum(&opnd);
        //     int b = popNum(&opnd);
        //     char op = popOp(&optr);
        //     pushNum(&opnd, calc(b, a, op));
        //     break;
        // }
        if (result == '<') {
            pushOp(&optr, s[i++]);
        } else if (result == '=') {
            popOp(&optr);
            i++;
        } else if (result == '>') {
            int a = popNum(&opnd);
            int b = popNum(&opnd);
            char op = popOp(&optr);
            pushNum(&opnd, calc(b, a, op));
        }
    }
    printf("%d\n", popNum(&opnd));
    return 0;
}