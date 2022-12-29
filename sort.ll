; ModuleID = "main"
target triple = "x86_64-pc-linux-gnu"
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"

declare i32 @"printf"(i8* %".1", ...)

declare i32 @"memset"(i32* %".1", i32 %".2", i32 %".3", ...)

declare i32 @"memcpy"(i32* %".1", i32* %".2", i32 %".3", ...)

define void @"swap"(i32* %".1", i32 %".2", i32 %".3")
{
swapentry:
  %"a" = alloca i32*
  store i32* %".1", i32** %"a"
  %"i" = alloca i32
  store i32 %".2", i32* %"i"
  %"j" = alloca i32
  store i32 %".3", i32* %"j"
  %".8" = load i32*, i32** %"a"
  %".9" = load i32, i32* %".8"
  %".10" = bitcast i32* %".8" to [0 x i32]*
  %".11" = load i32, i32* %"i"
  %".12" = getelementptr [0 x i32], [0 x i32]* %".10", i32 0, i32 %".11"
  %".13" = load i32, i32* %".12"
  %"b" = alloca i32
  store i32 %".13", i32* %"b"
  %".15" = load i32*, i32** %"a"
  %".16" = load i32, i32* %".15"
  %".17" = bitcast i32* %".15" to [0 x i32]*
  %".18" = load i32, i32* %"i"
  %".19" = getelementptr [0 x i32], [0 x i32]* %".17", i32 0, i32 %".18"
  %".20" = load i32, i32* %".19"
  %".21" = load i32*, i32** %"a"
  %".22" = load i32, i32* %".21"
  %".23" = bitcast i32* %".21" to [0 x i32]*
  %".24" = load i32, i32* %"j"
  %".25" = getelementptr [0 x i32], [0 x i32]* %".23", i32 0, i32 %".24"
  %".26" = load i32, i32* %".25"
  store i32 %".26", i32* %".19"
  %".28" = load i32*, i32** %"a"
  %".29" = load i32, i32* %".28"
  %".30" = bitcast i32* %".28" to [0 x i32]*
  %".31" = load i32, i32* %"j"
  %".32" = getelementptr [0 x i32], [0 x i32]* %".30", i32 0, i32 %".31"
  %".33" = load i32, i32* %".32"
  %".34" = load i32, i32* %"b"
  store i32 %".34", i32* %".32"
  ret void
}

define void @"insertSort"(i32* %".1", i32 %".2")
{
insertSortentry:
  %"a" = alloca i32*
  store i32* %".1", i32** %"a"
  %"n" = alloca i32
  store i32 %".2", i32* %"n"
  %"j" = alloca i32
  store i32 0, i32* %"j"
  %"k" = alloca i32
  %"i" = alloca i32
  store i32 1, i32* %"i"
  br label %"cond"
cond:
  %".9" = load i32, i32* %"n"
  %".10" = load i32, i32* %"i"
  %".11" = icmp slt i32 %".10", %".9"
  br i1 %".11", label %"stat", label %"quit"
stat:
  %".13" = load i32, i32* %"j"
  %".14" = load i32, i32* %"i"
  %".15" = sub i32 %".14", 1
  store i32 %".15", i32* %"j"
  %".17" = load i32, i32* %"k"
  %".18" = load i32*, i32** %"a"
  %".19" = load i32, i32* %".18"
  %".20" = bitcast i32* %".18" to [0 x i32]*
  %".21" = load i32, i32* %"i"
  %".22" = getelementptr [0 x i32], [0 x i32]* %".20", i32 0, i32 %".21"
  %".23" = load i32, i32* %".22"
  store i32 %".23", i32* %"k"
  br label %"cond.1"
quit:
  ret void
cond.1:
  %".26" = load i32, i32* %"k"
  %".27" = load i32*, i32** %"a"
  %".28" = load i32, i32* %".27"
  %".29" = bitcast i32* %".27" to [0 x i32]*
  %".30" = load i32, i32* %"j"
  %".31" = getelementptr [0 x i32], [0 x i32]* %".29", i32 0, i32 %".30"
  %".32" = load i32, i32* %".31"
  %".33" = icmp sgt i32 %".32", %".26"
  %".34" = load i32, i32* %"j"
  %".35" = icmp sge i32 %".34", 0
  %".36" = icmp ne i1 %".33", 0
  %".37" = icmp ne i1 %".35", 0
  %".38" = and i1 %".37", %".36"
  br i1 %".38", label %"stat.1", label %"quit.1"
stat.1:
  %".40" = load i32*, i32** %"a"
  %".41" = load i32, i32* %".40"
  %".42" = bitcast i32* %".40" to [0 x i32]*
  %".43" = load i32, i32* %"j"
  %".44" = add i32 %".43", 1
  %".45" = getelementptr [0 x i32], [0 x i32]* %".42", i32 0, i32 %".44"
  %".46" = load i32, i32* %".45"
  %".47" = load i32*, i32** %"a"
  %".48" = load i32, i32* %".47"
  %".49" = bitcast i32* %".47" to [0 x i32]*
  %".50" = load i32, i32* %"j"
  %".51" = getelementptr [0 x i32], [0 x i32]* %".49", i32 0, i32 %".50"
  %".52" = load i32, i32* %".51"
  store i32 %".52", i32* %".45"
  %".54" = load i32, i32* %"j"
  store i32 %".54", i32* %"j"
  %".56" = sub i32 %".54", 1
  store i32 %".56", i32* %"j"
  br label %"cond.1"
quit.1:
  %".59" = load i32*, i32** %"a"
  %".60" = load i32, i32* %".59"
  %".61" = bitcast i32* %".59" to [0 x i32]*
  %".62" = load i32, i32* %"j"
  %".63" = add i32 %".62", 1
  %".64" = getelementptr [0 x i32], [0 x i32]* %".61", i32 0, i32 %".63"
  %".65" = load i32, i32* %".64"
  %".66" = load i32, i32* %"k"
  store i32 %".66", i32* %".64"
  %".68" = load i32, i32* %"i"
  store i32 %".68", i32* %"i"
  %".70" = add i32 %".68", 1
  store i32 %".70", i32* %"i"
  br label %"cond"
}

define void @"quickSort"(i32* %".1", i32 %".2", i32 %".3")
{
quickSortentry:
  %"a" = alloca i32*
  store i32* %".1", i32** %"a"
  %"l" = alloca i32
  store i32 %".2", i32* %"l"
  %"r" = alloca i32
  store i32 %".3", i32* %"r"
  br label %"cond"
cond:
  %".9" = load i32, i32* %"r"
  %".10" = load i32, i32* %"l"
  %".11" = icmp sge i32 %".10", %".9"
  br i1 %".11", label %"stat", label %"quit"
stat:
  ret void
quit:
  %".15" = load i32*, i32** %"a"
  %".16" = load i32, i32* %".15"
  %".17" = bitcast i32* %".15" to [0 x i32]*
  %".18" = load i32, i32* %"r"
  %".19" = getelementptr [0 x i32], [0 x i32]* %".17", i32 0, i32 %".18"
  %".20" = load i32, i32* %".19"
  %"mid" = alloca i32
  store i32 %".20", i32* %"mid"
  %".22" = load i32, i32* %"l"
  %".23" = load i32, i32* %"r"
  %".24" = sub i32 %".23", 1
  %"l1" = alloca i32
  store i32 %".22", i32* %"l1"
  %"r1" = alloca i32
  store i32 %".24", i32* %"r1"
  br label %"cond.1"
cond.1:
  %".28" = load i32, i32* %"r1"
  %".29" = load i32, i32* %"l1"
  %".30" = icmp slt i32 %".29", %".28"
  br i1 %".30", label %"stat.1", label %"quit.1"
stat.1:
  br label %"cond.2"
quit.1:
  br label %"cond.4"
cond.2:
  %".33" = load i32, i32* %"r1"
  %".34" = load i32, i32* %"l1"
  %".35" = icmp slt i32 %".34", %".33"
  %".36" = load i32, i32* %"mid"
  %".37" = load i32*, i32** %"a"
  %".38" = load i32, i32* %".37"
  %".39" = bitcast i32* %".37" to [0 x i32]*
  %".40" = load i32, i32* %"l1"
  %".41" = getelementptr [0 x i32], [0 x i32]* %".39", i32 0, i32 %".40"
  %".42" = load i32, i32* %".41"
  %".43" = icmp slt i32 %".42", %".36"
  %".44" = icmp ne i1 %".35", 0
  %".45" = icmp ne i1 %".43", 0
  %".46" = and i1 %".45", %".44"
  br i1 %".46", label %"stat.2", label %"quit.2"
stat.2:
  %".48" = load i32, i32* %"l1"
  store i32 %".48", i32* %"l1"
  %".50" = add i32 %".48", 1
  store i32 %".50", i32* %"l1"
  br label %"cond.2"
quit.2:
  br label %"cond.3"
cond.3:
  %".54" = load i32, i32* %"r1"
  %".55" = load i32, i32* %"l1"
  %".56" = icmp slt i32 %".55", %".54"
  %".57" = load i32, i32* %"mid"
  %".58" = load i32*, i32** %"a"
  %".59" = load i32, i32* %".58"
  %".60" = bitcast i32* %".58" to [0 x i32]*
  %".61" = load i32, i32* %"r1"
  %".62" = getelementptr [0 x i32], [0 x i32]* %".60", i32 0, i32 %".61"
  %".63" = load i32, i32* %".62"
  %".64" = icmp sge i32 %".63", %".57"
  %".65" = icmp ne i1 %".56", 0
  %".66" = icmp ne i1 %".64", 0
  %".67" = and i1 %".66", %".65"
  br i1 %".67", label %"stat.3", label %"quit.3"
stat.3:
  %".69" = load i32, i32* %"r1"
  store i32 %".69", i32* %"r1"
  %".71" = sub i32 %".69", 1
  store i32 %".71", i32* %"r1"
  br label %"cond.3"
quit.3:
  %".74" = load i32*, i32** %"a"
  %".75" = load i32, i32* %"l1"
  %".76" = load i32, i32* %"r1"
  call void @"swap"(i32* %".74", i32 %".75", i32 %".76")
  br label %"cond.1"
cond.4:
  %".80" = load i32*, i32** %"a"
  %".81" = load i32, i32* %".80"
  %".82" = bitcast i32* %".80" to [0 x i32]*
  %".83" = load i32, i32* %"r"
  %".84" = getelementptr [0 x i32], [0 x i32]* %".82", i32 0, i32 %".83"
  %".85" = load i32, i32* %".84"
  %".86" = load i32*, i32** %"a"
  %".87" = load i32, i32* %".86"
  %".88" = bitcast i32* %".86" to [0 x i32]*
  %".89" = load i32, i32* %"l1"
  %".90" = getelementptr [0 x i32], [0 x i32]* %".88", i32 0, i32 %".89"
  %".91" = load i32, i32* %".90"
  %".92" = icmp sge i32 %".91", %".85"
  br i1 %".92", label %"stat.4", label %"else"
stat.4:
  %".94" = load i32*, i32** %"a"
  %".95" = load i32, i32* %"l1"
  %".96" = load i32, i32* %"r"
  call void @"swap"(i32* %".94", i32 %".95", i32 %".96")
  br label %"quit.4"
else:
  %".99" = load i32, i32* %"l1"
  store i32 %".99", i32* %"l1"
  %".101" = add i32 %".99", 1
  store i32 %".101", i32* %"l1"
  br label %"quit.4"
quit.4:
  %".104" = load i32*, i32** %"a"
  %".105" = load i32, i32* %"l"
  %".106" = load i32, i32* %"l1"
  %".107" = sub i32 %".106", 1
  call void @"quickSort"(i32* %".104", i32 %".105", i32 %".107")
  %".109" = load i32*, i32** %"a"
  %".110" = load i32, i32* %"l1"
  %".111" = add i32 %".110", 1
  %".112" = load i32, i32* %"r"
  call void @"quickSort"(i32* %".109", i32 %".111", i32 %".112")
  ret void
}

define void @"shellSort"(i32* %".1", i32 %".2")
{
shellSortentry:
  %"a" = alloca i32*
  store i32* %".1", i32** %"a"
  %"n" = alloca i32
  store i32 %".2", i32* %"n"
  %"i" = alloca i32
  %"j" = alloca i32
  %"gap" = alloca i32
  %"k" = alloca i32
  %".6" = load i32, i32* %"gap"
  %".7" = load i32, i32* %"n"
  %".8" = ashr i32 %".7", 1
  store i32 %".8", i32* %"gap"
  br label %"cond"
cond:
  %".11" = load i32, i32* %"gap"
  %".12" = icmp sgt i32 %".11", 0
  br i1 %".12", label %"stat", label %"quit"
stat:
  %".14" = load i32, i32* %"i"
  %".15" = load i32, i32* %"gap"
  store i32 %".15", i32* %"i"
  br label %"cond.1"
quit:
  ret void
cond.1:
  %".18" = load i32, i32* %"n"
  %".19" = load i32, i32* %"i"
  %".20" = icmp slt i32 %".19", %".18"
  br i1 %".20", label %"stat.1", label %"quit.1"
stat.1:
  %".22" = load i32, i32* %"k"
  %".23" = load i32*, i32** %"a"
  %".24" = load i32, i32* %".23"
  %".25" = bitcast i32* %".23" to [0 x i32]*
  %".26" = load i32, i32* %"i"
  %".27" = getelementptr [0 x i32], [0 x i32]* %".25", i32 0, i32 %".26"
  %".28" = load i32, i32* %".27"
  store i32 %".28", i32* %"k"
  %".30" = load i32, i32* %"j"
  %".31" = load i32, i32* %"gap"
  %".32" = load i32, i32* %"i"
  %".33" = sub i32 %".32", %".31"
  store i32 %".33", i32* %"j"
  br label %"cond.2"
quit.1:
  %".85" = load i32, i32* %"gap"
  %".86" = ashr i32 %".85", 1
  store i32 %".86", i32* %"gap"
  br label %"cond"
cond.2:
  %".36" = load i32, i32* %"k"
  %".37" = load i32*, i32** %"a"
  %".38" = load i32, i32* %".37"
  %".39" = bitcast i32* %".37" to [0 x i32]*
  %".40" = load i32, i32* %"j"
  %".41" = getelementptr [0 x i32], [0 x i32]* %".39", i32 0, i32 %".40"
  %".42" = load i32, i32* %".41"
  %".43" = icmp sgt i32 %".42", %".36"
  %".44" = load i32, i32* %"j"
  %".45" = icmp sge i32 %".44", 0
  %".46" = icmp ne i1 %".43", 0
  %".47" = icmp ne i1 %".45", 0
  %".48" = and i1 %".47", %".46"
  br i1 %".48", label %"stat.2", label %"quit.2"
stat.2:
  %".50" = load i32*, i32** %"a"
  %".51" = load i32, i32* %".50"
  %".52" = bitcast i32* %".50" to [0 x i32]*
  %".53" = load i32, i32* %"gap"
  %".54" = load i32, i32* %"j"
  %".55" = add i32 %".54", %".53"
  %".56" = getelementptr [0 x i32], [0 x i32]* %".52", i32 0, i32 %".55"
  %".57" = load i32, i32* %".56"
  %".58" = load i32*, i32** %"a"
  %".59" = load i32, i32* %".58"
  %".60" = bitcast i32* %".58" to [0 x i32]*
  %".61" = load i32, i32* %"j"
  %".62" = getelementptr [0 x i32], [0 x i32]* %".60", i32 0, i32 %".61"
  %".63" = load i32, i32* %".62"
  store i32 %".63", i32* %".56"
  %".65" = load i32, i32* %"j"
  %".66" = load i32, i32* %"gap"
  %".67" = sub i32 %".65", %".66"
  store i32 %".67", i32* %"j"
  br label %"cond.2"
quit.2:
  %".70" = load i32*, i32** %"a"
  %".71" = load i32, i32* %".70"
  %".72" = bitcast i32* %".70" to [0 x i32]*
  %".73" = load i32, i32* %"gap"
  %".74" = load i32, i32* %"j"
  %".75" = add i32 %".74", %".73"
  %".76" = getelementptr [0 x i32], [0 x i32]* %".72", i32 0, i32 %".75"
  %".77" = load i32, i32* %".76"
  %".78" = load i32, i32* %"k"
  store i32 %".78", i32* %".76"
  %".80" = load i32, i32* %"i"
  store i32 %".80", i32* %"i"
  %".82" = add i32 %".80", 1
  store i32 %".82", i32* %"i"
  br label %"cond.1"
}

define void @"merge"(i32* %".1", i32 %".2", i32 %".3", i32 %".4")
{
mergeentry:
  %"a" = alloca i32*
  store i32* %".1", i32** %"a"
  %"l" = alloca i32
  store i32 %".2", i32* %"l"
  %"m" = alloca i32
  store i32 %".3", i32* %"m"
  %"r" = alloca i32
  store i32 %".4", i32* %"r"
  %".10" = load i32, i32* %"l"
  %".11" = load i32, i32* %"m"
  %".12" = add i32 %".11", 1
  %"i" = alloca i32
  store i32 %".10", i32* %"i"
  %"j" = alloca i32
  store i32 %".12", i32* %"j"
  %"k" = alloca i32
  store i32 0, i32* %"k"
  %"b" = alloca [100 x i32]
  %".16" = bitcast [100 x i32]* %"b" to i32*
  %".17" = alloca i32*
  store i32* %".16", i32** %".17"
  br label %"cond"
cond:
  %".20" = load i32, i32* %"r"
  %".21" = load i32, i32* %"j"
  %".22" = icmp sle i32 %".21", %".20"
  %".23" = load i32, i32* %"m"
  %".24" = load i32, i32* %"i"
  %".25" = icmp sle i32 %".24", %".23"
  %".26" = icmp ne i1 %".22", 0
  %".27" = icmp ne i1 %".25", 0
  %".28" = and i1 %".27", %".26"
  br i1 %".28", label %"stat", label %"quit"
stat:
  br label %"cond.1"
quit:
  br label %"cond.2"
cond.1:
  %".31" = load i32*, i32** %"a"
  %".32" = load i32, i32* %".31"
  %".33" = bitcast i32* %".31" to [0 x i32]*
  %".34" = load i32, i32* %"j"
  %".35" = getelementptr [0 x i32], [0 x i32]* %".33", i32 0, i32 %".34"
  %".36" = load i32, i32* %".35"
  %".37" = load i32*, i32** %"a"
  %".38" = load i32, i32* %".37"
  %".39" = bitcast i32* %".37" to [0 x i32]*
  %".40" = load i32, i32* %"i"
  %".41" = getelementptr [0 x i32], [0 x i32]* %".39", i32 0, i32 %".40"
  %".42" = load i32, i32* %".41"
  %".43" = icmp slt i32 %".42", %".36"
  br i1 %".43", label %"stat.1", label %"else"
stat.1:
  %".45" = load i32*, i32** %".17"
  %".46" = load i32, i32* %".45"
  %".47" = bitcast i32* %".45" to [0 x i32]*
  %".48" = load i32, i32* %"k"
  store i32 %".48", i32* %"k"
  %".50" = add i32 %".48", 1
  store i32 %".50", i32* %"k"
  %".52" = getelementptr [0 x i32], [0 x i32]* %".47", i32 0, i32 %".48"
  %".53" = load i32, i32* %".52"
  %".54" = load i32*, i32** %"a"
  %".55" = load i32, i32* %".54"
  %".56" = bitcast i32* %".54" to [0 x i32]*
  %".57" = load i32, i32* %"i"
  store i32 %".57", i32* %"i"
  %".59" = add i32 %".57", 1
  store i32 %".59", i32* %"i"
  %".61" = getelementptr [0 x i32], [0 x i32]* %".56", i32 0, i32 %".57"
  %".62" = load i32, i32* %".61"
  store i32 %".62", i32* %".52"
  br label %"quit.1"
else:
  %".65" = load i32*, i32** %".17"
  %".66" = load i32, i32* %".65"
  %".67" = bitcast i32* %".65" to [0 x i32]*
  %".68" = load i32, i32* %"k"
  store i32 %".68", i32* %"k"
  %".70" = add i32 %".68", 1
  store i32 %".70", i32* %"k"
  %".72" = getelementptr [0 x i32], [0 x i32]* %".67", i32 0, i32 %".68"
  %".73" = load i32, i32* %".72"
  %".74" = load i32*, i32** %"a"
  %".75" = load i32, i32* %".74"
  %".76" = bitcast i32* %".74" to [0 x i32]*
  %".77" = load i32, i32* %"j"
  store i32 %".77", i32* %"j"
  %".79" = add i32 %".77", 1
  store i32 %".79", i32* %"j"
  %".81" = getelementptr [0 x i32], [0 x i32]* %".76", i32 0, i32 %".77"
  %".82" = load i32, i32* %".81"
  store i32 %".82", i32* %".72"
  br label %"quit.1"
quit.1:
  br label %"cond"
cond.2:
  %".87" = load i32, i32* %"m"
  %".88" = load i32, i32* %"i"
  %".89" = icmp sle i32 %".88", %".87"
  br i1 %".89", label %"stat.2", label %"quit.2"
stat.2:
  %".91" = load i32*, i32** %".17"
  %".92" = load i32, i32* %".91"
  %".93" = bitcast i32* %".91" to [0 x i32]*
  %".94" = load i32, i32* %"k"
  store i32 %".94", i32* %"k"
  %".96" = add i32 %".94", 1
  store i32 %".96", i32* %"k"
  %".98" = getelementptr [0 x i32], [0 x i32]* %".93", i32 0, i32 %".94"
  %".99" = load i32, i32* %".98"
  %".100" = load i32*, i32** %"a"
  %".101" = load i32, i32* %".100"
  %".102" = bitcast i32* %".100" to [0 x i32]*
  %".103" = load i32, i32* %"i"
  store i32 %".103", i32* %"i"
  %".105" = add i32 %".103", 1
  store i32 %".105", i32* %"i"
  %".107" = getelementptr [0 x i32], [0 x i32]* %".102", i32 0, i32 %".103"
  %".108" = load i32, i32* %".107"
  store i32 %".108", i32* %".98"
  br label %"cond.2"
quit.2:
  br label %"cond.3"
cond.3:
  %".112" = load i32, i32* %"r"
  %".113" = load i32, i32* %"j"
  %".114" = icmp sle i32 %".113", %".112"
  br i1 %".114", label %"stat.3", label %"quit.3"
stat.3:
  %".116" = load i32*, i32** %".17"
  %".117" = load i32, i32* %".116"
  %".118" = bitcast i32* %".116" to [0 x i32]*
  %".119" = load i32, i32* %"k"
  store i32 %".119", i32* %"k"
  %".121" = add i32 %".119", 1
  store i32 %".121", i32* %"k"
  %".123" = getelementptr [0 x i32], [0 x i32]* %".118", i32 0, i32 %".119"
  %".124" = load i32, i32* %".123"
  %".125" = load i32*, i32** %"a"
  %".126" = load i32, i32* %".125"
  %".127" = bitcast i32* %".125" to [0 x i32]*
  %".128" = load i32, i32* %"j"
  store i32 %".128", i32* %"j"
  %".130" = add i32 %".128", 1
  store i32 %".130", i32* %"j"
  %".132" = getelementptr [0 x i32], [0 x i32]* %".127", i32 0, i32 %".128"
  %".133" = load i32, i32* %".132"
  store i32 %".133", i32* %".123"
  br label %"cond.3"
quit.3:
  %".136" = load i32, i32* %"i"
  %".137" = load i32, i32* %"l"
  store i32 %".137", i32* %"i"
  %".139" = load i32, i32* %"k"
  store i32 0, i32* %"k"
  br label %"cond.4"
cond.4:
  %".142" = load i32, i32* %"r"
  %".143" = load i32, i32* %"i"
  %".144" = icmp sle i32 %".143", %".142"
  br i1 %".144", label %"stat.4", label %"quit.4"
stat.4:
  %".146" = load i32*, i32** %"a"
  %".147" = load i32, i32* %".146"
  %".148" = bitcast i32* %".146" to [0 x i32]*
  %".149" = load i32, i32* %"i"
  %".150" = getelementptr [0 x i32], [0 x i32]* %".148", i32 0, i32 %".149"
  %".151" = load i32, i32* %".150"
  %".152" = load i32*, i32** %".17"
  %".153" = load i32, i32* %".152"
  %".154" = bitcast i32* %".152" to [0 x i32]*
  %".155" = load i32, i32* %"k"
  %".156" = getelementptr [0 x i32], [0 x i32]* %".154", i32 0, i32 %".155"
  %".157" = load i32, i32* %".156"
  store i32 %".157", i32* %".150"
  %".159" = load i32, i32* %"i"
  store i32 %".159", i32* %"i"
  %".161" = add i32 %".159", 1
  store i32 %".161", i32* %"i"
  %".163" = load i32, i32* %"k"
  store i32 %".163", i32* %"k"
  %".165" = add i32 %".163", 1
  store i32 %".165", i32* %"k"
  br label %"cond.4"
quit.4:
  ret void
}

define void @"mergeSort"(i32* %".1", i32 %".2", i32 %".3")
{
mergeSortentry:
  %"a" = alloca i32*
  store i32* %".1", i32** %"a"
  %"l" = alloca i32
  store i32 %".2", i32* %"l"
  %"r" = alloca i32
  store i32 %".3", i32* %"r"
  br label %"cond"
cond:
  %".9" = load i32, i32* %"r"
  %".10" = load i32, i32* %"l"
  %".11" = icmp sge i32 %".10", %".9"
  br i1 %".11", label %"stat", label %"quit"
stat:
  ret void
quit:
  %".15" = load i32, i32* %"r"
  %".16" = load i32, i32* %"l"
  %".17" = add i32 %".16", %".15"
  %".18" = ashr i32 %".17", 1
  %"m" = alloca i32
  store i32 %".18", i32* %"m"
  %".20" = load i32*, i32** %"a"
  %".21" = load i32, i32* %"l"
  %".22" = load i32, i32* %"m"
  call void @"mergeSort"(i32* %".20", i32 %".21", i32 %".22")
  %".24" = load i32*, i32** %"a"
  %".25" = load i32, i32* %"m"
  %".26" = add i32 %".25", 1
  %".27" = load i32, i32* %"r"
  call void @"mergeSort"(i32* %".24", i32 %".26", i32 %".27")
  %".29" = load i32*, i32** %"a"
  %".30" = load i32, i32* %"l"
  %".31" = load i32, i32* %"m"
  %".32" = load i32, i32* %"r"
  call void @"merge"(i32* %".29", i32 %".30", i32 %".31", i32 %".32")
  ret void
}

define void @"radixSort"(i32* %".1", i32 %".2")
{
radixSortentry:
  %"a" = alloca i32*
  store i32* %".1", i32** %"a"
  %"n" = alloca i32
  store i32 %".2", i32* %"n"
  %"i" = alloca i32
  %"exp" = alloca i32
  store i32 1, i32* %"exp"
  %".7" = load i32*, i32** %"a"
  %".8" = load i32, i32* %".7"
  %".9" = bitcast i32* %".7" to [0 x i32]*
  %".10" = getelementptr [0 x i32], [0 x i32]* %".9", i32 0, i32 0
  %".11" = load i32, i32* %".10"
  %"m" = alloca i32
  store i32 %".11", i32* %"m"
  %".13" = load i32, i32* %"i"
  store i32 1, i32* %"i"
  br label %"cond"
cond:
  %".16" = load i32, i32* %"n"
  %".17" = load i32, i32* %"i"
  %".18" = icmp slt i32 %".17", %".16"
  br i1 %".18", label %"stat", label %"quit"
stat:
  br label %"cond.1"
quit:
  br label %"cond.2"
cond.1:
  %".21" = load i32, i32* %"m"
  %".22" = load i32*, i32** %"a"
  %".23" = load i32, i32* %".22"
  %".24" = bitcast i32* %".22" to [0 x i32]*
  %".25" = load i32, i32* %"i"
  %".26" = getelementptr [0 x i32], [0 x i32]* %".24", i32 0, i32 %".25"
  %".27" = load i32, i32* %".26"
  %".28" = icmp sgt i32 %".27", %".21"
  br i1 %".28", label %"stat.1", label %"quit.1"
stat.1:
  %".30" = load i32, i32* %"m"
  %".31" = load i32*, i32** %"a"
  %".32" = load i32, i32* %".31"
  %".33" = bitcast i32* %".31" to [0 x i32]*
  %".34" = load i32, i32* %"i"
  %".35" = getelementptr [0 x i32], [0 x i32]* %".33", i32 0, i32 %".34"
  %".36" = load i32, i32* %".35"
  store i32 %".36", i32* %"m"
  br label %"quit.1"
quit.1:
  %".39" = load i32, i32* %"i"
  store i32 %".39", i32* %"i"
  %".41" = add i32 %".39", 1
  store i32 %".41", i32* %"i"
  br label %"cond"
cond.2:
  %".45" = load i32, i32* %"exp"
  %".46" = load i32, i32* %"m"
  %".47" = sdiv i32 %".46", %".45"
  %".48" = icmp sgt i32 %".47", 1
  br i1 %".48", label %"stat.2", label %"quit.2"
stat.2:
  %"b" = alloca [100 x i32]
  %".50" = bitcast [100 x i32]* %"b" to i32*
  %".51" = alloca i32*
  store i32* %".50", i32** %".51"
  %"bucket" = alloca [10 x i32]
  %".53" = bitcast [10 x i32]* %"bucket" to i32*
  %".54" = alloca i32*
  store i32* %".53", i32** %".54"
  %".56" = load i32*, i32** %".54"
  %".57" = mul i32 10, 4
  %".58" = call i32 (i32*, i32, i32, ...) @"memset"(i32* %".56", i32 0, i32 %".57")
  %".59" = load i32, i32* %"i"
  store i32 0, i32* %"i"
  br label %"cond.3"
quit.2:
  ret void
cond.3:
  %".62" = load i32, i32* %"n"
  %".63" = load i32, i32* %"i"
  %".64" = icmp slt i32 %".63", %".62"
  br i1 %".64", label %"stat.3", label %"quit.3"
stat.3:
  %".66" = load i32*, i32** %".54"
  %".67" = load i32, i32* %".66"
  %".68" = bitcast i32* %".66" to [0 x i32]*
  %".69" = load i32, i32* %"exp"
  %".70" = load i32*, i32** %"a"
  %".71" = load i32, i32* %".70"
  %".72" = bitcast i32* %".70" to [0 x i32]*
  %".73" = load i32, i32* %"i"
  %".74" = getelementptr [0 x i32], [0 x i32]* %".72", i32 0, i32 %".73"
  %".75" = load i32, i32* %".74"
  %".76" = sdiv i32 %".75", %".69"
  %".77" = srem i32 %".76", 10
  %".78" = getelementptr [0 x i32], [0 x i32]* %".68", i32 0, i32 %".77"
  %".79" = load i32, i32* %".78"
  store i32 %".79", i32* %".78"
  %".81" = add i32 %".79", 1
  store i32 %".81", i32* %".78"
  %".83" = load i32, i32* %"i"
  store i32 %".83", i32* %"i"
  %".85" = add i32 %".83", 1
  store i32 %".85", i32* %"i"
  br label %"cond.3"
quit.3:
  %".88" = load i32, i32* %"i"
  store i32 1, i32* %"i"
  br label %"cond.4"
cond.4:
  %".91" = load i32, i32* %"i"
  %".92" = icmp slt i32 %".91", 10
  br i1 %".92", label %"stat.4", label %"quit.4"
stat.4:
  %".94" = load i32*, i32** %".54"
  %".95" = load i32, i32* %".94"
  %".96" = bitcast i32* %".94" to [0 x i32]*
  %".97" = load i32, i32* %"i"
  %".98" = getelementptr [0 x i32], [0 x i32]* %".96", i32 0, i32 %".97"
  %".99" = load i32, i32* %".98"
  %".100" = load i32*, i32** %".54"
  %".101" = load i32, i32* %".100"
  %".102" = bitcast i32* %".100" to [0 x i32]*
  %".103" = load i32, i32* %"i"
  %".104" = sub i32 %".103", 1
  %".105" = getelementptr [0 x i32], [0 x i32]* %".102", i32 0, i32 %".104"
  %".106" = load i32, i32* %".105"
  %".107" = add i32 %".99", %".106"
  store i32 %".107", i32* %".98"
  %".109" = load i32, i32* %"i"
  store i32 %".109", i32* %"i"
  %".111" = add i32 %".109", 1
  store i32 %".111", i32* %"i"
  br label %"cond.4"
quit.4:
  %".114" = load i32, i32* %"i"
  %".115" = load i32, i32* %"n"
  %".116" = sub i32 %".115", 1
  store i32 %".116", i32* %"i"
  br label %"cond.5"
cond.5:
  %".119" = load i32, i32* %"i"
  %".120" = icmp sge i32 %".119", 0
  br i1 %".120", label %"stat.5", label %"quit.5"
stat.5:
  %".122" = load i32*, i32** %".51"
  %".123" = load i32, i32* %".122"
  %".124" = bitcast i32* %".122" to [0 x i32]*
  %".125" = load i32*, i32** %".54"
  %".126" = load i32, i32* %".125"
  %".127" = bitcast i32* %".125" to [0 x i32]*
  %".128" = load i32, i32* %"exp"
  %".129" = load i32*, i32** %"a"
  %".130" = load i32, i32* %".129"
  %".131" = bitcast i32* %".129" to [0 x i32]*
  %".132" = load i32, i32* %"i"
  %".133" = getelementptr [0 x i32], [0 x i32]* %".131", i32 0, i32 %".132"
  %".134" = load i32, i32* %".133"
  %".135" = sdiv i32 %".134", %".128"
  %".136" = srem i32 %".135", 10
  %".137" = getelementptr [0 x i32], [0 x i32]* %".127", i32 0, i32 %".136"
  %".138" = load i32, i32* %".137"
  %".139" = sub i32 %".138", 1
  store i32 %".139", i32* %".137"
  %".141" = getelementptr [0 x i32], [0 x i32]* %".124", i32 0, i32 %".139"
  %".142" = load i32, i32* %".141"
  %".143" = load i32*, i32** %"a"
  %".144" = load i32, i32* %".143"
  %".145" = bitcast i32* %".143" to [0 x i32]*
  %".146" = load i32, i32* %"i"
  %".147" = getelementptr [0 x i32], [0 x i32]* %".145", i32 0, i32 %".146"
  %".148" = load i32, i32* %".147"
  store i32 %".148", i32* %".141"
  %".150" = load i32, i32* %"i"
  store i32 %".150", i32* %"i"
  %".152" = sub i32 %".150", 1
  store i32 %".152", i32* %"i"
  br label %"cond.5"
quit.5:
  %".155" = load i32*, i32** %"a"
  %".156" = load i32*, i32** %".51"
  %".157" = load i32, i32* %"n"
  %".158" = mul i32 %".157", 4
  %".159" = call i32 (i32*, i32*, i32, ...) @"memcpy"(i32* %".155", i32* %".156", i32 %".158")
  %".160" = load i32, i32* %"exp"
  %".161" = mul i32 %".160", 10
  store i32 %".161", i32* %"exp"
  br label %"cond.2"
}

define void @"print"(i32* %".1", i32 %".2")
{
printentry:
  %"a" = alloca i32*
  store i32* %".1", i32** %"a"
  %"n" = alloca i32
  store i32 %".2", i32* %"n"
  %"i" = alloca i32
  store i32 0, i32* %"i"
  br label %"cond"
cond:
  %".8" = load i32, i32* %"n"
  %".9" = load i32, i32* %"i"
  %".10" = icmp slt i32 %".9", %".8"
  br i1 %".10", label %"stat", label %"quit"
stat:
  %".12" = alloca [4 x i8]
  store [4 x i8] [i8 37, i8 117, i8 32, i8 0], [4 x i8]* %".12"
  %".14" = bitcast [4 x i8]* %".12" to i8*
  %".15" = load i32*, i32** %"a"
  %".16" = load i32, i32* %".15"
  %".17" = bitcast i32* %".15" to [0 x i32]*
  %".18" = load i32, i32* %"i"
  %".19" = getelementptr [0 x i32], [0 x i32]* %".17", i32 0, i32 %".18"
  %".20" = load i32, i32* %".19"
  %".21" = call i32 (i8*, ...) @"printf"(i8* %".14", i32 %".20")
  %".22" = load i32, i32* %"i"
  store i32 %".22", i32* %"i"
  %".24" = add i32 %".22", 1
  store i32 %".24", i32* %"i"
  br label %"cond"
quit:
  %".27" = alloca [2 x i8]
  store [2 x i8] [i8 10, i8 0], [2 x i8]* %".27"
  %".29" = bitcast [2 x i8]* %".27" to i8*
  %".30" = call i32 (i8*, ...) @"printf"(i8* %".29")
  ret void
}

define i32 @"main"()
{
mainentry:
  %"a" = alloca [10 x i32]
  %".2" = getelementptr [10 x i32], [10 x i32]* %"a", i32 0, i32 0
  store i32 1, i32* %".2"
  %".4" = getelementptr [10 x i32], [10 x i32]* %"a", i32 0, i32 1
  store i32 3, i32* %".4"
  %".6" = getelementptr [10 x i32], [10 x i32]* %"a", i32 0, i32 2
  store i32 2, i32* %".6"
  %".8" = getelementptr [10 x i32], [10 x i32]* %"a", i32 0, i32 3
  store i32 5, i32* %".8"
  %".10" = getelementptr [10 x i32], [10 x i32]* %"a", i32 0, i32 4
  store i32 4, i32* %".10"
  %".12" = getelementptr [10 x i32], [10 x i32]* %"a", i32 0, i32 5
  store i32 7, i32* %".12"
  %".14" = getelementptr [10 x i32], [10 x i32]* %"a", i32 0, i32 6
  store i32 6, i32* %".14"
  %".16" = getelementptr [10 x i32], [10 x i32]* %"a", i32 0, i32 7
  store i32 9, i32* %".16"
  %".18" = getelementptr [10 x i32], [10 x i32]* %"a", i32 0, i32 8
  store i32 8, i32* %".18"
  %".20" = getelementptr [10 x i32], [10 x i32]* %"a", i32 0, i32 9
  store i32 0, i32* %".20"
  %".22" = bitcast [10 x i32]* %"a" to i32*
  %".23" = alloca i32*
  store i32* %".22", i32** %".23"
  %"b" = alloca [10 x i32]
  %".25" = getelementptr [10 x i32], [10 x i32]* %"b", i32 0, i32 0
  store i32 1, i32* %".25"
  %".27" = getelementptr [10 x i32], [10 x i32]* %"b", i32 0, i32 1
  store i32 3, i32* %".27"
  %".29" = getelementptr [10 x i32], [10 x i32]* %"b", i32 0, i32 2
  store i32 2, i32* %".29"
  %".31" = getelementptr [10 x i32], [10 x i32]* %"b", i32 0, i32 3
  store i32 5, i32* %".31"
  %".33" = getelementptr [10 x i32], [10 x i32]* %"b", i32 0, i32 4
  store i32 4, i32* %".33"
  %".35" = getelementptr [10 x i32], [10 x i32]* %"b", i32 0, i32 5
  store i32 7, i32* %".35"
  %".37" = getelementptr [10 x i32], [10 x i32]* %"b", i32 0, i32 6
  store i32 6, i32* %".37"
  %".39" = getelementptr [10 x i32], [10 x i32]* %"b", i32 0, i32 7
  store i32 9, i32* %".39"
  %".41" = getelementptr [10 x i32], [10 x i32]* %"b", i32 0, i32 8
  store i32 8, i32* %".41"
  %".43" = getelementptr [10 x i32], [10 x i32]* %"b", i32 0, i32 9
  store i32 0, i32* %".43"
  %".45" = bitcast [10 x i32]* %"b" to i32*
  %".46" = alloca i32*
  store i32* %".45", i32** %".46"
  %"c" = alloca [10 x i32]
  %".48" = getelementptr [10 x i32], [10 x i32]* %"c", i32 0, i32 0
  store i32 1, i32* %".48"
  %".50" = getelementptr [10 x i32], [10 x i32]* %"c", i32 0, i32 1
  store i32 3, i32* %".50"
  %".52" = getelementptr [10 x i32], [10 x i32]* %"c", i32 0, i32 2
  store i32 2, i32* %".52"
  %".54" = getelementptr [10 x i32], [10 x i32]* %"c", i32 0, i32 3
  store i32 5, i32* %".54"
  %".56" = getelementptr [10 x i32], [10 x i32]* %"c", i32 0, i32 4
  store i32 4, i32* %".56"
  %".58" = getelementptr [10 x i32], [10 x i32]* %"c", i32 0, i32 5
  store i32 7, i32* %".58"
  %".60" = getelementptr [10 x i32], [10 x i32]* %"c", i32 0, i32 6
  store i32 6, i32* %".60"
  %".62" = getelementptr [10 x i32], [10 x i32]* %"c", i32 0, i32 7
  store i32 9, i32* %".62"
  %".64" = getelementptr [10 x i32], [10 x i32]* %"c", i32 0, i32 8
  store i32 8, i32* %".64"
  %".66" = getelementptr [10 x i32], [10 x i32]* %"c", i32 0, i32 9
  store i32 0, i32* %".66"
  %".68" = bitcast [10 x i32]* %"c" to i32*
  %".69" = alloca i32*
  store i32* %".68", i32** %".69"
  %"d" = alloca [10 x i32]
  %".71" = getelementptr [10 x i32], [10 x i32]* %"d", i32 0, i32 0
  store i32 1, i32* %".71"
  %".73" = getelementptr [10 x i32], [10 x i32]* %"d", i32 0, i32 1
  store i32 3, i32* %".73"
  %".75" = getelementptr [10 x i32], [10 x i32]* %"d", i32 0, i32 2
  store i32 2, i32* %".75"
  %".77" = getelementptr [10 x i32], [10 x i32]* %"d", i32 0, i32 3
  store i32 5, i32* %".77"
  %".79" = getelementptr [10 x i32], [10 x i32]* %"d", i32 0, i32 4
  store i32 4, i32* %".79"
  %".81" = getelementptr [10 x i32], [10 x i32]* %"d", i32 0, i32 5
  store i32 7, i32* %".81"
  %".83" = getelementptr [10 x i32], [10 x i32]* %"d", i32 0, i32 6
  store i32 6, i32* %".83"
  %".85" = getelementptr [10 x i32], [10 x i32]* %"d", i32 0, i32 7
  store i32 9, i32* %".85"
  %".87" = getelementptr [10 x i32], [10 x i32]* %"d", i32 0, i32 8
  store i32 8, i32* %".87"
  %".89" = getelementptr [10 x i32], [10 x i32]* %"d", i32 0, i32 9
  store i32 0, i32* %".89"
  %".91" = bitcast [10 x i32]* %"d" to i32*
  %".92" = alloca i32*
  store i32* %".91", i32** %".92"
  %"e" = alloca [10 x i32]
  %".94" = getelementptr [10 x i32], [10 x i32]* %"e", i32 0, i32 0
  store i32 1, i32* %".94"
  %".96" = getelementptr [10 x i32], [10 x i32]* %"e", i32 0, i32 1
  store i32 3, i32* %".96"
  %".98" = getelementptr [10 x i32], [10 x i32]* %"e", i32 0, i32 2
  store i32 2, i32* %".98"
  %".100" = getelementptr [10 x i32], [10 x i32]* %"e", i32 0, i32 3
  store i32 5, i32* %".100"
  %".102" = getelementptr [10 x i32], [10 x i32]* %"e", i32 0, i32 4
  store i32 4, i32* %".102"
  %".104" = getelementptr [10 x i32], [10 x i32]* %"e", i32 0, i32 5
  store i32 7, i32* %".104"
  %".106" = getelementptr [10 x i32], [10 x i32]* %"e", i32 0, i32 6
  store i32 6, i32* %".106"
  %".108" = getelementptr [10 x i32], [10 x i32]* %"e", i32 0, i32 7
  store i32 9, i32* %".108"
  %".110" = getelementptr [10 x i32], [10 x i32]* %"e", i32 0, i32 8
  store i32 8, i32* %".110"
  %".112" = getelementptr [10 x i32], [10 x i32]* %"e", i32 0, i32 9
  store i32 0, i32* %".112"
  %".114" = bitcast [10 x i32]* %"e" to i32*
  %".115" = alloca i32*
  store i32* %".114", i32** %".115"
  %"n" = alloca i32
  store i32 10, i32* %"n"
  %".118" = alloca [13 x i8]
  store [13 x i8] [i8 73, i8 110, i8 115, i8 101, i8 114, i8 116, i8 83, i8 111, i8 114, i8 116, i8 58, i8 10, i8 0], [13 x i8]* %".118"
  %".120" = bitcast [13 x i8]* %".118" to i8*
  %".121" = call i32 (i8*, ...) @"printf"(i8* %".120")
  %".122" = load i32*, i32** %".23"
  %".123" = load i32, i32* %"n"
  call void @"print"(i32* %".122", i32 %".123")
  %".125" = load i32*, i32** %".23"
  %".126" = load i32, i32* %"n"
  call void @"insertSort"(i32* %".125", i32 %".126")
  %".128" = load i32*, i32** %".23"
  %".129" = load i32, i32* %"n"
  call void @"print"(i32* %".128", i32 %".129")
  %".131" = alloca [12 x i8]
  store [12 x i8] [i8 77, i8 101, i8 114, i8 103, i8 101, i8 83, i8 111, i8 114, i8 116, i8 58, i8 10, i8 0], [12 x i8]* %".131"
  %".133" = bitcast [12 x i8]* %".131" to i8*
  %".134" = call i32 (i8*, ...) @"printf"(i8* %".133")
  %".135" = load i32*, i32** %".92"
  %".136" = load i32, i32* %"n"
  call void @"print"(i32* %".135", i32 %".136")
  %".138" = load i32*, i32** %".92"
  %".139" = load i32, i32* %"n"
  %".140" = sub i32 %".139", 1
  call void @"mergeSort"(i32* %".138", i32 0, i32 %".140")
  %".142" = load i32*, i32** %".92"
  %".143" = load i32, i32* %"n"
  call void @"print"(i32* %".142", i32 %".143")
  %".145" = alloca [12 x i8]
  store [12 x i8] [i8 81, i8 117, i8 105, i8 99, i8 107, i8 83, i8 111, i8 114, i8 116, i8 58, i8 10, i8 0], [12 x i8]* %".145"
  %".147" = bitcast [12 x i8]* %".145" to i8*
  %".148" = call i32 (i8*, ...) @"printf"(i8* %".147")
  %".149" = load i32*, i32** %".46"
  %".150" = load i32, i32* %"n"
  call void @"print"(i32* %".149", i32 %".150")
  %".152" = load i32*, i32** %".46"
  %".153" = load i32, i32* %"n"
  %".154" = sub i32 %".153", 1
  call void @"quickSort"(i32* %".152", i32 0, i32 %".154")
  %".156" = load i32*, i32** %".46"
  %".157" = load i32, i32* %"n"
  call void @"print"(i32* %".156", i32 %".157")
  %".159" = alloca [12 x i8]
  store [12 x i8] [i8 83, i8 104, i8 101, i8 108, i8 108, i8 83, i8 111, i8 114, i8 116, i8 58, i8 10, i8 0], [12 x i8]* %".159"
  %".161" = bitcast [12 x i8]* %".159" to i8*
  %".162" = call i32 (i8*, ...) @"printf"(i8* %".161")
  %".163" = load i32*, i32** %".69"
  %".164" = load i32, i32* %"n"
  call void @"print"(i32* %".163", i32 %".164")
  %".166" = load i32*, i32** %".69"
  %".167" = load i32, i32* %"n"
  call void @"shellSort"(i32* %".166", i32 %".167")
  %".169" = load i32*, i32** %".69"
  %".170" = load i32, i32* %"n"
  call void @"print"(i32* %".169", i32 %".170")
  %".172" = alloca [12 x i8]
  store [12 x i8] [i8 82, i8 97, i8 100, i8 105, i8 120, i8 83, i8 111, i8 114, i8 116, i8 58, i8 10, i8 0], [12 x i8]* %".172"
  %".174" = bitcast [12 x i8]* %".172" to i8*
  %".175" = call i32 (i8*, ...) @"printf"(i8* %".174")
  %".176" = load i32*, i32** %".115"
  %".177" = load i32, i32* %"n"
  call void @"print"(i32* %".176", i32 %".177")
  %".179" = load i32*, i32** %".115"
  %".180" = load i32, i32* %"n"
  call void @"radixSort"(i32* %".179", i32 %".180")
  %".182" = load i32*, i32** %".115"
  %".183" = load i32, i32* %"n"
  call void @"print"(i32* %".182", i32 %".183")
  ret i32 0
}
