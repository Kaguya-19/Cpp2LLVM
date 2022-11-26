运行lex.py实现词法分析。

指令示例： python lex.py < ./test/calculator.cpp。

Token分为四个部分：类型、值、起始位置、结束位置。
如下是一个Token的示例，其中类型是Float，值是float，起始位置是第1171，结束位置是第1171。
<Float, float, 1171, 1176>