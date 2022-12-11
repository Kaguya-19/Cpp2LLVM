********** 词法分析 **********
运行lex.py实现词法分析。

指令示例： python lex.py < ./test/calculator.cpp。

Token分为四个部分：类型、值、起始位置、结束位置。
如下是一个Token的示例，其中类型是Float，值是float，起始位置是第1171，结束位置是第1171。
<Float, float, 1171, 1176>

********** 语法分析 **********
需要安装antlr4.7.2,教程见https://zhuanlan.zhihu.com/p/423928097
运行parserByAntlr.py实现语法分析。
默认将语法分析树生成在tree.json中，可以自己指定输出文件。