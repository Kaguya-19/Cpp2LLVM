#define MAX_LENGTH 2000

int calculate(char* input){
    char operation[MAX_LENGTH];
    int num_new[MAX_LENGTH];
    int present_num = -1, present_operator = -1, decimal_process = 1, len_str;
    int num = 0, i;

    len_str = strlen(input);
    for(i = strlen(input) - 1; i >= 0; i = i - 1) 
    {
        input[i + 1] = input[i];
    }
    i = len_str + 1;
    input[0] = '(';
    input[len_str + 1] = ')';
    len_str = len_str + 2;
    while(i >= 0){
        if(input[i] == '+')
        {
            while(present_operator >= 0 && ((operation[present_operator] == '*') || (operation[present_operator] == '/'))){
                if(operation[present_operator] == '*') {
                    num_new[present_num - 1] = num_new[present_num] * num_new[present_num - 1];
                }
                else {
                    num_new[present_num - 1] = num_new[present_num] / num_new[present_num - 1];
                }
                present_operator = present_operator - 1;
                present_num = present_num - 1;
            }
            present_operator = present_operator + 1;
            operation[present_operator] = '+';
            i = i - 1;
        }
        else if(input[i] == '*' || input[i] == '/' || input[i] == ')')
        {
            present_operator = present_operator + 1;
            operation[present_operator] = input[i];
            i = i - 1;
        }
        else if(input[i] == '-')
        {
            while(present_operator >= 0 && ((operation[present_operator] == '*') || (operation[present_operator] == '/'))){
                if(operation[present_operator] == '*'){
                    num_new[present_num - 1] = num_new[present_num] * num_new[present_num - 1];
                }
                else{
                    num_new[present_num - 1] = num_new[present_num] / num_new[present_num - 1];
                }
                present_num = present_num - 1;
                present_operator = present_operator - 1;
            }
            present_operator = present_operator + 1;
            operation[present_operator] = '-';
            i = i - 1;
        }
        else if(input[i] == '(')
        {
            while(operation[present_operator] != ')')
            {
                char OperatorGet = operation[present_operator];
                present_operator = present_operator - 1;
                if(OperatorGet == '/'){
                    num_new[present_num - 1] = num_new[present_num] / num_new[present_num - 1];
                }
                else if(OperatorGet == '+'){
                    num_new[present_num - 1] = num_new[present_num] + num_new[present_num - 1];
                }
                else if(OperatorGet == '-'){
                    num_new[present_num - 1] = num_new[present_num] - num_new[present_num - 1];
                }
                else if(OperatorGet == '*'){
                    num_new[present_num - 1] = num_new[present_num] * num_new[present_num - 1];
                }
                present_num = present_num - 1;
            }
            present_operator = present_operator - 1;
            i = i - 1;
        }
        else
        {
            num = 0;
            decimal_process = 1;
            while(i >= 0 && input[i] <= '9' && input[i] >= '0')
            {
                num = num + (input[i] - '0') * decimal_process;
                decimal_process = decimal_process * 10;
                i = i - 1;
            }
            num_new[present_num + 1] = num;
            present_num = present_num + 1;
        }
    }
    return num_new[0]
}

int main()
{
    char input[MAX_LENGTH];
    printf("please input an calculate expression: ");
    gets(input);
    
    int result = calculate(input);
    printf("result: %d\n", result);
    return 0;
}