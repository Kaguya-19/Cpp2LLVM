; ModuleID = "main"
target triple = "x86_64-pc-linux-gnu"
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"

%"opndStack" = type {i32*, i32}
%"optrStack" = type {i8*, i32}
declare i32 @"gets"(i8* %".1", ...)

declare i32 @"printf"(i8* %".1", ...)

declare i32 @"strlen"(i8* %".1", ...)

declare i32 @"memset"(i8* %".1", i32 %".2", i32 %".3", ...)

declare i32 @"atoi"(i8* %".1", ...)

define i32 @"charToInt"(i8 %".1")
{
charToIntentry:
  %"c" = alloca i8
  store i8 %".1", i8* %"c"
  %"res" = alloca i32
  br label %"cond"
cond:
  %".5" = load i8, i8* %"c"
  %".6" = icmp eq i8 %".5", 43
  br i1 %".6", label %"stat", label %"else"
stat:
  %".8" = load i32, i32* %"res"
  store i32 0, i32* %"res"
  br label %"quit"
else:
  br label %"cond.1"
quit:
  %".59" = load i32, i32* %"res"
  ret i32 %".59"
cond.1:
  %".12" = load i8, i8* %"c"
  %".13" = icmp eq i8 %".12", 45
  br i1 %".13", label %"stat.1", label %"else.1"
stat.1:
  %".15" = load i32, i32* %"res"
  store i32 1, i32* %"res"
  br label %"quit.1"
else.1:
  br label %"cond.2"
quit.1:
  br label %"quit"
cond.2:
  %".19" = load i8, i8* %"c"
  %".20" = icmp eq i8 %".19", 42
  br i1 %".20", label %"stat.2", label %"else.2"
stat.2:
  %".22" = load i32, i32* %"res"
  store i32 2, i32* %"res"
  br label %"quit.2"
else.2:
  br label %"cond.3"
quit.2:
  br label %"quit.1"
cond.3:
  %".26" = load i8, i8* %"c"
  %".27" = icmp eq i8 %".26", 47
  br i1 %".27", label %"stat.3", label %"else.3"
stat.3:
  %".29" = load i32, i32* %"res"
  store i32 3, i32* %"res"
  br label %"quit.3"
else.3:
  br label %"cond.4"
quit.3:
  br label %"quit.2"
cond.4:
  %".33" = load i8, i8* %"c"
  %".34" = icmp eq i8 %".33", 40
  br i1 %".34", label %"stat.4", label %"else.4"
stat.4:
  %".36" = load i32, i32* %"res"
  store i32 4, i32* %"res"
  br label %"quit.4"
else.4:
  br label %"cond.5"
quit.4:
  br label %"quit.3"
cond.5:
  %".40" = load i8, i8* %"c"
  %".41" = icmp eq i8 %".40", 41
  br i1 %".41", label %"stat.5", label %"else.5"
stat.5:
  %".43" = load i32, i32* %"res"
  store i32 5, i32* %"res"
  br label %"quit.5"
else.5:
  br label %"cond.6"
quit.5:
  br label %"quit.4"
cond.6:
  %".47" = load i8, i8* %"c"
  %".48" = icmp eq i8 %".47", 0
  br i1 %".48", label %"stat.6", label %"quit.6"
stat.6:
  %".50" = load i32, i32* %"res"
  store i32 6, i32* %"res"
  br label %"quit.6"
quit.6:
  br label %"quit.5"
}

define i8 @"orderBetweenOp"(i8 %".1", i8 %".2")
{
orderBetweenOpentry:
  %"op1" = alloca i8
  store i8 %".1", i8* %"op1"
  %"op2" = alloca i8
  store i8 %".2", i8* %"op2"
  %"pri" = alloca [49 x i8]
  %".6" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 0
  store i8 62, i8* %".6"
  %".8" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 1
  store i8 62, i8* %".8"
  %".10" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 2
  store i8 60, i8* %".10"
  %".12" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 3
  store i8 60, i8* %".12"
  %".14" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 4
  store i8 60, i8* %".14"
  %".16" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 5
  store i8 62, i8* %".16"
  %".18" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 6
  store i8 62, i8* %".18"
  %".20" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 7
  store i8 62, i8* %".20"
  %".22" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 8
  store i8 62, i8* %".22"
  %".24" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 9
  store i8 60, i8* %".24"
  %".26" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 10
  store i8 60, i8* %".26"
  %".28" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 11
  store i8 60, i8* %".28"
  %".30" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 12
  store i8 62, i8* %".30"
  %".32" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 13
  store i8 62, i8* %".32"
  %".34" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 14
  store i8 62, i8* %".34"
  %".36" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 15
  store i8 62, i8* %".36"
  %".38" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 16
  store i8 62, i8* %".38"
  %".40" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 17
  store i8 62, i8* %".40"
  %".42" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 18
  store i8 60, i8* %".42"
  %".44" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 19
  store i8 62, i8* %".44"
  %".46" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 20
  store i8 62, i8* %".46"
  %".48" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 21
  store i8 62, i8* %".48"
  %".50" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 22
  store i8 62, i8* %".50"
  %".52" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 23
  store i8 62, i8* %".52"
  %".54" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 24
  store i8 62, i8* %".54"
  %".56" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 25
  store i8 60, i8* %".56"
  %".58" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 26
  store i8 62, i8* %".58"
  %".60" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 27
  store i8 62, i8* %".60"
  %".62" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 28
  store i8 60, i8* %".62"
  %".64" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 29
  store i8 60, i8* %".64"
  %".66" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 30
  store i8 60, i8* %".66"
  %".68" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 31
  store i8 60, i8* %".68"
  %".70" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 32
  store i8 60, i8* %".70"
  %".72" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 33
  store i8 61, i8* %".72"
  %".74" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 34
  store i8 32, i8* %".74"
  %".76" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 35
  store i8 32, i8* %".76"
  %".78" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 36
  store i8 32, i8* %".78"
  %".80" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 37
  store i8 32, i8* %".80"
  %".82" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 38
  store i8 32, i8* %".82"
  %".84" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 39
  store i8 32, i8* %".84"
  %".86" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 40
  store i8 32, i8* %".86"
  %".88" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 41
  store i8 32, i8* %".88"
  %".90" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 42
  store i8 60, i8* %".90"
  %".92" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 43
  store i8 60, i8* %".92"
  %".94" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 44
  store i8 60, i8* %".94"
  %".96" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 45
  store i8 60, i8* %".96"
  %".98" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 46
  store i8 60, i8* %".98"
  %".100" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 47
  store i8 32, i8* %".100"
  %".102" = getelementptr [49 x i8], [49 x i8]* %"pri", i32 0, i32 48
  store i8 61, i8* %".102"
  %".104" = bitcast [49 x i8]* %"pri" to i8*
  %".105" = alloca i8*
  store i8* %".104", i8** %".105"
  %".107" = load i8, i8* %"op1"
  %".108" = call i32 @"charToInt"(i8 %".107")
  %"x" = alloca i32
  store i32 %".108", i32* %"x"
  %".110" = load i8, i8* %"op2"
  %".111" = call i32 @"charToInt"(i8 %".110")
  %"y" = alloca i32
  store i32 %".111", i32* %"y"
  %".113" = load i8*, i8** %".105"
  %".114" = load i8, i8* %".113"
  %".115" = bitcast i8* %".113" to [0 x i8]*
  %".116" = load i32, i32* %"y"
  %".117" = load i32, i32* %"x"
  %".118" = mul i32 %".117", 7
  %".119" = add i32 %".118", %".116"
  %".120" = getelementptr [0 x i8], [0 x i8]* %".115", i32 0, i32 %".119"
  %".121" = load i8, i8* %".120"
  ret i8 %".121"
}

define i32 @"calc"(i32 %".1", i32 %".2", i8 %".3")
{
calcentry:
  %"a" = alloca i32
  store i32 %".1", i32* %"a"
  %"b" = alloca i32
  store i32 %".2", i32* %"b"
  %"op" = alloca i8
  store i8 %".3", i8* %"op"
  br label %"cond"
cond:
  %".9" = load i8, i8* %"op"
  %".10" = icmp eq i8 %".9", 43
  br i1 %".10", label %"stat", label %"quit"
stat:
  %".12" = load i32, i32* %"b"
  %".13" = load i32, i32* %"a"
  %".14" = add i32 %".13", %".12"
  ret i32 %".14"
quit:
  br label %"cond.1"
cond.1:
  %".18" = load i8, i8* %"op"
  %".19" = icmp eq i8 %".18", 45
  br i1 %".19", label %"stat.1", label %"quit.1"
stat.1:
  %".21" = load i32, i32* %"b"
  %".22" = load i32, i32* %"a"
  %".23" = sub i32 %".22", %".21"
  ret i32 %".23"
quit.1:
  br label %"cond.2"
cond.2:
  %".27" = load i8, i8* %"op"
  %".28" = icmp eq i8 %".27", 42
  br i1 %".28", label %"stat.2", label %"quit.2"
stat.2:
  %".30" = load i32, i32* %"b"
  %".31" = load i32, i32* %"a"
  %".32" = mul i32 %".31", %".30"
  ret i32 %".32"
quit.2:
  br label %"cond.3"
cond.3:
  %".36" = load i8, i8* %"op"
  %".37" = icmp eq i8 %".36", 47
  br i1 %".37", label %"stat.3", label %"quit.3"
stat.3:
  %".39" = load i32, i32* %"b"
  %".40" = load i32, i32* %"a"
  %".41" = sdiv i32 %".40", %".39"
  ret i32 %".41"
quit.3:
  ret i32 0
}

define i32 @"popNum"(%"opndStack"* %".1")
{
popNumentry:
  %"stack" = alloca %"opndStack"*
  store %"opndStack"* %".1", %"opndStack"** %"stack"
  br label %"cond"
cond:
  %".5" = load %"opndStack"*, %"opndStack"** %"stack"
  %".6" = getelementptr %"opndStack", %"opndStack"* %".5", i32 0, i32 1
  %".7" = load i32, i32* %".6"
  %".8" = icmp sge i32 %".7", 0
  br i1 %".8", label %"stat", label %"quit"
stat:
  %".10" = load %"opndStack"*, %"opndStack"** %"stack"
  %".11" = getelementptr %"opndStack", %"opndStack"* %".10", i32 0, i32 1
  %".12" = load i32, i32* %".11"
  %".13" = sub i32 %".12", 1
  store i32 %".13", i32* %".11"
  %".15" = load %"opndStack"*, %"opndStack"** %"stack"
  %".16" = getelementptr %"opndStack", %"opndStack"* %".15", i32 0, i32 0
  %".17" = load i32*, i32** %".16"
  %".18" = load i32, i32* %".17"
  %".19" = bitcast i32* %".17" to [0 x i32]*
  %".20" = load %"opndStack"*, %"opndStack"** %"stack"
  %".21" = getelementptr %"opndStack", %"opndStack"* %".20", i32 0, i32 1
  %".22" = load i32, i32* %".21"
  %".23" = add i32 %".22", 1
  %".24" = getelementptr [0 x i32], [0 x i32]* %".19", i32 0, i32 %".23"
  %".25" = load i32, i32* %".24"
  ret i32 %".25"
quit:
  ret i32 0
}

define void @"pushNum"(%"opndStack"* %".1", i32 %".2")
{
pushNumentry:
  %"stack" = alloca %"opndStack"*
  store %"opndStack"* %".1", %"opndStack"** %"stack"
  %"num" = alloca i32
  store i32 %".2", i32* %"num"
  %".6" = load %"opndStack"*, %"opndStack"** %"stack"
  %".7" = getelementptr %"opndStack", %"opndStack"* %".6", i32 0, i32 0
  %".8" = load i32*, i32** %".7"
  %".9" = load i32, i32* %".8"
  %".10" = bitcast i32* %".8" to [0 x i32]*
  %".11" = load %"opndStack"*, %"opndStack"** %"stack"
  %".12" = getelementptr %"opndStack", %"opndStack"* %".11", i32 0, i32 1
  %".13" = load i32, i32* %".12"
  %".14" = add i32 %".13", 1
  store i32 %".14", i32* %".12"
  %".16" = getelementptr [0 x i32], [0 x i32]* %".10", i32 0, i32 %".14"
  %".17" = load i32, i32* %".16"
  %".18" = load i32, i32* %"num"
  store i32 %".18", i32* %".16"
  ret void
}

define i8 @"popOp"(%"optrStack"* %".1")
{
popOpentry:
  %"stack" = alloca %"optrStack"*
  store %"optrStack"* %".1", %"optrStack"** %"stack"
  br label %"cond"
cond:
  %".5" = load %"optrStack"*, %"optrStack"** %"stack"
  %".6" = getelementptr %"optrStack", %"optrStack"* %".5", i32 0, i32 1
  %".7" = load i32, i32* %".6"
  %".8" = icmp sge i32 %".7", 1
  br i1 %".8", label %"stat", label %"quit"
stat:
  %".10" = load %"optrStack"*, %"optrStack"** %"stack"
  %".11" = getelementptr %"optrStack", %"optrStack"* %".10", i32 0, i32 0
  %".12" = load i8*, i8** %".11"
  %".13" = load i8, i8* %".12"
  %".14" = bitcast i8* %".12" to [0 x i8]*
  %".15" = load %"optrStack"*, %"optrStack"** %"stack"
  %".16" = getelementptr %"optrStack", %"optrStack"* %".15", i32 0, i32 1
  %".17" = load i32, i32* %".16"
  store i32 %".17", i32* %".16"
  %".19" = sub i32 %".17", 1
  store i32 %".19", i32* %".16"
  %".21" = getelementptr [0 x i8], [0 x i8]* %".14", i32 0, i32 %".17"
  %".22" = load i8, i8* %".21"
  ret i8 %".22"
quit:
  ret i8 0
}

define void @"pushOp"(%"optrStack"* %".1", i8 %".2")
{
pushOpentry:
  %"stack" = alloca %"optrStack"*
  store %"optrStack"* %".1", %"optrStack"** %"stack"
  %"op" = alloca i8
  store i8 %".2", i8* %"op"
  %".6" = load %"optrStack"*, %"optrStack"** %"stack"
  %".7" = getelementptr %"optrStack", %"optrStack"* %".6", i32 0, i32 1
  %".8" = load i32, i32* %".7"
  store i32 %".8", i32* %".7"
  %".10" = add i32 %".8", 1
  store i32 %".10", i32* %".7"
  %".12" = load %"optrStack"*, %"optrStack"** %"stack"
  %".13" = getelementptr %"optrStack", %"optrStack"* %".12", i32 0, i32 0
  %".14" = load i8*, i8** %".13"
  %".15" = load i8, i8* %".14"
  %".16" = bitcast i8* %".14" to [0 x i8]*
  %".17" = load %"optrStack"*, %"optrStack"** %"stack"
  %".18" = getelementptr %"optrStack", %"optrStack"* %".17", i32 0, i32 1
  %".19" = load i32, i32* %".18"
  %".20" = getelementptr [0 x i8], [0 x i8]* %".16", i32 0, i32 %".19"
  %".21" = load i8, i8* %".20"
  %".22" = load i8, i8* %"op"
  store i8 %".22", i8* %".20"
  ret void
}

define i8 @"topOp"(%"optrStack"* %".1")
{
topOpentry:
  %"stack" = alloca %"optrStack"*
  store %"optrStack"* %".1", %"optrStack"** %"stack"
  br label %"cond"
cond:
  %".5" = load %"optrStack"*, %"optrStack"** %"stack"
  %".6" = getelementptr %"optrStack", %"optrStack"* %".5", i32 0, i32 1
  %".7" = load i32, i32* %".6"
  %".8" = icmp slt i32 %".7", 0
  br i1 %".8", label %"stat", label %"quit"
stat:
  ret i8 48
quit:
  %".12" = load %"optrStack"*, %"optrStack"** %"stack"
  %".13" = getelementptr %"optrStack", %"optrStack"* %".12", i32 0, i32 0
  %".14" = load i8*, i8** %".13"
  %".15" = load i8, i8* %".14"
  %".16" = bitcast i8* %".14" to [0 x i8]*
  %".17" = load %"optrStack"*, %"optrStack"** %"stack"
  %".18" = getelementptr %"optrStack", %"optrStack"* %".17", i32 0, i32 1
  %".19" = load i32, i32* %".18"
  %".20" = getelementptr [0 x i8], [0 x i8]* %".16", i32 0, i32 %".19"
  %".21" = load i8, i8* %".20"
  ret i8 %".21"
}

define i32 @"main"()
{
mainentry:
  %"s" = alloca [512 x i8]
  %".2" = bitcast [512 x i8]* %"s" to i8*
  %".3" = alloca i8*
  store i8* %".2", i8** %".3"
  %".5" = alloca [30 x i8]
  store [30 x i8] [i8 112, i8 108, i8 101, i8 97, i8 115, i8 101, i8 32, i8 105, i8 110, i8 112, i8 117, i8 116, i8 32, i8 116, i8 104, i8 101, i8 32, i8 101, i8 120, i8 112, i8 114, i8 101, i8 115, i8 115, i8 105, i8 111, i8 110, i8 58, i8 32, i8 0], [30 x i8]* %".5"
  %".7" = bitcast [30 x i8]* %".5" to i8*
  %".8" = call i32 (i8*, ...) @"printf"(i8* %".7")
  %".9" = load i8*, i8** %".3"
  %".10" = call i32 (i8*, ...) @"gets"(i8* %".9")
  %".11" = load i8*, i8** %".3"
  %".12" = call i32 (i8*, ...) @"strlen"(i8* %".11")
  %"expr_len" = alloca i32
  store i32 %".12", i32* %"expr_len"
  %".14" = alloca %"opndStack"
  %"opndData" = alloca [512 x i32]
  %".15" = bitcast [512 x i32]* %"opndData" to i32*
  %".16" = alloca i32*
  store i32* %".15", i32** %".16"
  %".18" = getelementptr %"opndStack", %"opndStack"* %".14", i32 0, i32 1
  %".19" = load i32, i32* %".18"
  %".20" = sub i32 0, 1
  store i32 %".20", i32* %".18"
  %".22" = getelementptr %"opndStack", %"opndStack"* %".14", i32 0, i32 0
  %".23" = load i32*, i32** %".22"
  %".24" = load i32*, i32** %".16"
  store i32* %".24", i32** %".22"
  %".26" = alloca %"optrStack"
  %"optrData" = alloca [512 x i8]
  %".27" = bitcast [512 x i8]* %"optrData" to i8*
  %".28" = alloca i8*
  store i8* %".27", i8** %".28"
  %".30" = getelementptr %"optrStack", %"optrStack"* %".26", i32 0, i32 1
  %".31" = load i32, i32* %".30"
  %".32" = sub i32 0, 1
  store i32 %".32", i32* %".30"
  %".34" = getelementptr %"optrStack", %"optrStack"* %".26", i32 0, i32 0
  %".35" = load i8*, i8** %".34"
  %".36" = load i8*, i8** %".28"
  store i8* %".36", i8** %".34"
  %"currentNum" = alloca [15 x i8]
  %".38" = bitcast [15 x i8]* %"currentNum" to i8*
  %".39" = alloca i8*
  store i8* %".38", i8** %".39"
  %".41" = load i8*, i8** %".39"
  %".42" = call i32 (i8*, i32, i32, ...) @"memset"(i8* %".41", i32 0, i32 15)
  %".43" = load %"optrStack", %"optrStack"* %".26"
  call void @"pushOp"(%"optrStack"* %".26", i8 0)
  %"i" = alloca i32
  store i32 0, i32* %"i"
  %"k" = alloca i32
  store i32 0, i32* %"k"
  br label %"cond"
cond:
  %".48" = load i32, i32* %"expr_len"
  %".49" = load i32, i32* %"i"
  %".50" = icmp sle i32 %".49", %".48"
  br i1 %".50", label %"stat", label %"quit"
stat:
  br label %"cond.1"
quit:
  %".165" = alloca [4 x i8]
  store [4 x i8] [i8 37, i8 100, i8 10, i8 0], [4 x i8]* %".165"
  %".167" = bitcast [4 x i8]* %".165" to i8*
  %".168" = load %"opndStack", %"opndStack"* %".14"
  %".169" = call i32 @"popNum"(%"opndStack"* %".14")
  %".170" = call i32 (i8*, ...) @"printf"(i8* %".167", i32 %".169")
  ret i32 0
cond.1:
  %".53" = load i8*, i8** %".3"
  %".54" = load i8, i8* %".53"
  %".55" = bitcast i8* %".53" to [0 x i8]*
  %".56" = load i32, i32* %"i"
  %".57" = getelementptr [0 x i8], [0 x i8]* %".55", i32 0, i32 %".56"
  %".58" = load i8, i8* %".57"
  %".59" = icmp sle i8 %".58", 57
  %".60" = load i8*, i8** %".3"
  %".61" = load i8, i8* %".60"
  %".62" = bitcast i8* %".60" to [0 x i8]*
  %".63" = load i32, i32* %"i"
  %".64" = getelementptr [0 x i8], [0 x i8]* %".62", i32 0, i32 %".63"
  %".65" = load i8, i8* %".64"
  %".66" = icmp sge i8 %".65", 48
  %".67" = icmp ne i1 %".59", 0
  %".68" = icmp ne i1 %".66", 0
  %".69" = and i1 %".68", %".67"
  br i1 %".69", label %"stat.1", label %"quit.1"
stat.1:
  %".71" = load i8*, i8** %".39"
  %".72" = load i8, i8* %".71"
  %".73" = bitcast i8* %".71" to [0 x i8]*
  %".74" = load i32, i32* %"k"
  store i32 %".74", i32* %"k"
  %".76" = add i32 %".74", 1
  store i32 %".76", i32* %"k"
  %".78" = getelementptr [0 x i8], [0 x i8]* %".73", i32 0, i32 %".74"
  %".79" = load i8, i8* %".78"
  %".80" = load i8*, i8** %".3"
  %".81" = load i8, i8* %".80"
  %".82" = bitcast i8* %".80" to [0 x i8]*
  %".83" = load i32, i32* %"i"
  store i32 %".83", i32* %"i"
  %".85" = add i32 %".83", 1
  store i32 %".85", i32* %"i"
  %".87" = getelementptr [0 x i8], [0 x i8]* %".82", i32 0, i32 %".83"
  %".88" = load i8, i8* %".87"
  store i8 %".88", i8* %".78"
  br label %"cond"
quit.1:
  br label %"cond.2"
cond.2:
  %".93" = load i32, i32* %"k"
  %".94" = icmp ne i32 %".93", 0
  br i1 %".94", label %"stat.2", label %"quit.2"
stat.2:
  %".96" = load %"opndStack", %"opndStack"* %".14"
  %".97" = load i8*, i8** %".39"
  %".98" = call i32 (i8*, ...) @"atoi"(i8* %".97")
  call void @"pushNum"(%"opndStack"* %".14", i32 %".98")
  %".100" = load i8*, i8** %".39"
  %".101" = call i32 (i8*, i32, i32, ...) @"memset"(i8* %".100", i32 0, i32 15)
  %".102" = load i32, i32* %"k"
  store i32 0, i32* %"k"
  br label %"quit.2"
quit.2:
  %".105" = load %"optrStack", %"optrStack"* %".26"
  %".106" = call i8 @"topOp"(%"optrStack"* %".26")
  %".107" = load i8*, i8** %".3"
  %".108" = load i8, i8* %".107"
  %".109" = bitcast i8* %".107" to [0 x i8]*
  %".110" = load i32, i32* %"i"
  %".111" = getelementptr [0 x i8], [0 x i8]* %".109", i32 0, i32 %".110"
  %".112" = load i8, i8* %".111"
  %".113" = call i8 @"orderBetweenOp"(i8 %".106", i8 %".112")
  %"result" = alloca i8
  store i8 %".113", i8* %"result"
  br label %"cond.3"
cond.3:
  %".116" = load i8, i8* %"result"
  %".117" = icmp eq i8 %".116", 60
  br i1 %".117", label %"stat.3", label %"else"
stat.3:
  %".119" = load %"optrStack", %"optrStack"* %".26"
  %".120" = load i8*, i8** %".3"
  %".121" = load i8, i8* %".120"
  %".122" = bitcast i8* %".120" to [0 x i8]*
  %".123" = load i32, i32* %"i"
  store i32 %".123", i32* %"i"
  %".125" = add i32 %".123", 1
  store i32 %".125", i32* %"i"
  %".127" = getelementptr [0 x i8], [0 x i8]* %".122", i32 0, i32 %".123"
  %".128" = load i8, i8* %".127"
  call void @"pushOp"(%"optrStack"* %".26", i8 %".128")
  br label %"quit.3"
else:
  br label %"cond.4"
quit.3:
  br label %"cond"
cond.4:
  %".132" = load i8, i8* %"result"
  %".133" = icmp eq i8 %".132", 61
  br i1 %".133", label %"stat.4", label %"else.1"
stat.4:
  %".135" = load %"optrStack", %"optrStack"* %".26"
  %".136" = call i8 @"popOp"(%"optrStack"* %".26")
  %".137" = load i32, i32* %"i"
  store i32 %".137", i32* %"i"
  %".139" = add i32 %".137", 1
  store i32 %".139", i32* %"i"
  br label %"quit.4"
else.1:
  br label %"cond.5"
quit.4:
  br label %"quit.3"
cond.5:
  %".143" = load i8, i8* %"result"
  %".144" = icmp eq i8 %".143", 62
  br i1 %".144", label %"stat.5", label %"quit.5"
stat.5:
  %".146" = load %"opndStack", %"opndStack"* %".14"
  %".147" = call i32 @"popNum"(%"opndStack"* %".14")
  %"a" = alloca i32
  store i32 %".147", i32* %"a"
  %".149" = load %"opndStack", %"opndStack"* %".14"
  %".150" = call i32 @"popNum"(%"opndStack"* %".14")
  %"b" = alloca i32
  store i32 %".150", i32* %"b"
  %".152" = load %"optrStack", %"optrStack"* %".26"
  %".153" = call i8 @"popOp"(%"optrStack"* %".26")
  %"op" = alloca i8
  store i8 %".153", i8* %"op"
  %".155" = load %"opndStack", %"opndStack"* %".14"
  %".156" = load i32, i32* %"b"
  %".157" = load i32, i32* %"a"
  %".158" = load i8, i8* %"op"
  %".159" = call i32 @"calc"(i32 %".156", i32 %".157", i8 %".158")
  call void @"pushNum"(%"opndStack"* %".14", i32 %".159")
  br label %"quit.5"
quit.5:
  br label %"quit.4"
}
