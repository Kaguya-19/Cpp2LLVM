; ModuleID = ""
target triple = "x86_64-pc-linux-gnu"
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"

declare i32 @"printf"(i8* %".1", ...)

declare i32 @"gets"(i8* %".1", ...)

declare i32 @"strlen"(i8* %".1", ...)

define i32 @"main"()
{
main.entry:
  %"text" = alloca [1024 x i8]
  %".2" = bitcast [1024 x i8]* %"text" to i8*
  %".3" = alloca i8*
  store i8* %".2", i8** %".3"
  %"key" = alloca [1024 x i8]
  %".5" = bitcast [1024 x i8]* %"key" to i8*
  %".6" = alloca i8*
  store i8* %".5", i8** %".6"
  %"kmpTable" = alloca [1024 x i32]
  %".8" = bitcast [1024 x i32]* %"kmpTable" to i32*
  %".9" = alloca i32*
  store i32* %".8", i32** %".9"
  %"textLen" = alloca i32
  %"keyLen" = alloca i32
  %"i" = alloca i32
  %"j" = alloca i32
  %"matched" = alloca i32
  store i32 0, i32* %"matched"
  %"k" = alloca i32
  %".12" = alloca [23 x i8]
  store [23 x i8] [i8 80, i8 108, i8 101, i8 97, i8 115, i8 101, i8 32, i8 105, i8 110, i8 112, i8 117, i8 116, i8 32, i8 116, i8 104, i8 101, i8 32, i8 116, i8 101, i8 120, i8 116, i8 58, i8 0], [23 x i8]* %".12"
  %".14" = bitcast [23 x i8]* %".12" to i8*
  %".15" = call i32 (i8*, ...) @"printf"(i8* %".14")
  %".16" = load i8*, i8** %".3"
  %".17" = call i32 (i8*, ...) @"gets"(i8* %".16")
  %".18" = alloca [22 x i8]
  store [22 x i8] [i8 80, i8 108, i8 101, i8 97, i8 115, i8 101, i8 32, i8 105, i8 110, i8 112, i8 117, i8 116, i8 32, i8 116, i8 104, i8 101, i8 32, i8 107, i8 101, i8 121, i8 58, i8 0], [22 x i8]* %".18"
  %".20" = bitcast [22 x i8]* %".18" to i8*
  %".21" = call i32 (i8*, ...) @"printf"(i8* %".20")
  %".22" = load i8*, i8** %".6"
  %".23" = call i32 (i8*, ...) @"gets"(i8* %".22")
  %".24" = load i32*, i32** %".9"
  %".25" = load i32, i32* %".24"
  %".26" = bitcast i32* %".24" to [0 x i32]*
  %".27" = getelementptr [0 x i32], [0 x i32]* %".26", i32 0, i32 0
  %".28" = load i32, i32* %".27"
  %".29" = sub i32 0, 1
  store i32 %".29", i32* %".27"
  %".31" = load i32, i32* %"textLen"
  %".32" = load i8*, i8** %".3"
  %".33" = call i32 (i8*, ...) @"strlen"(i8* %".32")
  store i32 %".33", i32* %"textLen"
  %".35" = load i32, i32* %"keyLen"
  %".36" = load i8*, i8** %".6"
  %".37" = call i32 (i8*, ...) @"strlen"(i8* %".36")
  store i32 %".37", i32* %"keyLen"
  %".39" = load i32, i32* %"i"
  store i32 1, i32* %"i"
  %".41" = load i32, i32* %"j"
  %".42" = sub i32 0, 1
  store i32 %".42", i32* %"j"
  br label %"cond"
cond:
  %".45" = load i32, i32* %"keyLen"
  %".46" = load i32, i32* %"i"
  %".47" = icmp slt i32 %".46", %".45"
  br i1 %".47", label %"stat", label %"quit"
stat:
  br label %"cond.1"
quit:
  %".113" = load i32, i32* %"i"
  store i32 0, i32* %"i"
  %".115" = load i32, i32* %"j"
  %".116" = sub i32 0, 1
  store i32 %".116", i32* %"j"
  br label %"cond.3"
cond.1:
  %".50" = load i8*, i8** %".6"
  %".51" = load i8, i8* %".50"
  %".52" = bitcast i8* %".50" to [0 x i8]*
  %".53" = load i32, i32* %"j"
  %".54" = add i32 %".53", 1
  %".55" = getelementptr [0 x i8], [0 x i8]* %".52", i32 0, i32 %".54"
  %".56" = load i8, i8* %".55"
  %".57" = load i8*, i8** %".6"
  %".58" = load i8, i8* %".57"
  %".59" = bitcast i8* %".57" to [0 x i8]*
  %".60" = load i32, i32* %"i"
  %".61" = getelementptr [0 x i8], [0 x i8]* %".59", i32 0, i32 %".60"
  %".62" = load i8, i8* %".61"
  %".63" = icmp ne i8 %".62", %".56"
  %".64" = load i32, i32* %"j"
  %".65" = icmp sge i32 %".64", 0
  %".66" = icmp ne i1 %".63", 0
  %".67" = icmp ne i1 %".65", 0
  %".68" = and i1 %".67", %".66"
  br i1 %".68", label %"stat.1", label %"quit.1"
stat.1:
  %".70" = load i32, i32* %"j"
  %".71" = load i32*, i32** %".9"
  %".72" = load i32, i32* %".71"
  %".73" = bitcast i32* %".71" to [0 x i32]*
  %".74" = load i32, i32* %"j"
  %".75" = getelementptr [0 x i32], [0 x i32]* %".73", i32 0, i32 %".74"
  %".76" = load i32, i32* %".75"
  store i32 %".76", i32* %"j"
  br label %"cond.1"
quit.1:
  br label %"cond.2"
cond.2:
  %".80" = load i8*, i8** %".6"
  %".81" = load i8, i8* %".80"
  %".82" = bitcast i8* %".80" to [0 x i8]*
  %".83" = load i32, i32* %"j"
  %".84" = add i32 %".83", 1
  %".85" = getelementptr [0 x i8], [0 x i8]* %".82", i32 0, i32 %".84"
  %".86" = load i8, i8* %".85"
  %".87" = load i8*, i8** %".6"
  %".88" = load i8, i8* %".87"
  %".89" = bitcast i8* %".87" to [0 x i8]*
  %".90" = load i32, i32* %"i"
  %".91" = getelementptr [0 x i8], [0 x i8]* %".89", i32 0, i32 %".90"
  %".92" = load i8, i8* %".91"
  %".93" = icmp eq i8 %".92", %".86"
  br i1 %".93", label %"stat.2", label %"quit.2"
stat.2:
  %".95" = load i32, i32* %"j"
  %".96" = load i32, i32* %"j"
  %".97" = add i32 %".96", 1
  store i32 %".97", i32* %"j"
  br label %"quit.2"
quit.2:
  %".100" = load i32*, i32** %".9"
  %".101" = load i32, i32* %".100"
  %".102" = bitcast i32* %".100" to [0 x i32]*
  %".103" = load i32, i32* %"i"
  %".104" = getelementptr [0 x i32], [0 x i32]* %".102", i32 0, i32 %".103"
  %".105" = load i32, i32* %".104"
  %".106" = load i32, i32* %"j"
  store i32 %".106", i32* %".104"
  %".108" = load i32, i32* %"i"
  %".109" = load i32, i32* %"i"
  %".110" = add i32 %".109", 1
  store i32 %".110", i32* %"i"
  br label %"cond"
cond.3:
  %".119" = load i32, i32* %"textLen"
  %".120" = load i32, i32* %"i"
  %".121" = icmp slt i32 %".120", %".119"
  br i1 %".121", label %"stat.3", label %"quit.3"
stat.3:
  br label %"cond.4"
quit.3:
  br label %"cond.7"
cond.4:
  %".124" = load i8*, i8** %".6"
  %".125" = load i8, i8* %".124"
  %".126" = bitcast i8* %".124" to [0 x i8]*
  %".127" = load i32, i32* %"j"
  %".128" = add i32 %".127", 1
  %".129" = getelementptr [0 x i8], [0 x i8]* %".126", i32 0, i32 %".128"
  %".130" = load i8, i8* %".129"
  %".131" = load i8*, i8** %".3"
  %".132" = load i8, i8* %".131"
  %".133" = bitcast i8* %".131" to [0 x i8]*
  %".134" = load i32, i32* %"i"
  %".135" = getelementptr [0 x i8], [0 x i8]* %".133", i32 0, i32 %".134"
  %".136" = load i8, i8* %".135"
  %".137" = icmp ne i8 %".136", %".130"
  %".138" = load i32, i32* %"j"
  %".139" = icmp sge i32 %".138", 0
  %".140" = icmp ne i1 %".137", 0
  %".141" = icmp ne i1 %".139", 0
  %".142" = and i1 %".141", %".140"
  br i1 %".142", label %"stat.4", label %"quit.4"
stat.4:
  %".144" = load i32, i32* %"j"
  %".145" = load i32*, i32** %".9"
  %".146" = load i32, i32* %".145"
  %".147" = bitcast i32* %".145" to [0 x i32]*
  %".148" = load i32, i32* %"j"
  %".149" = getelementptr [0 x i32], [0 x i32]* %".147", i32 0, i32 %".148"
  %".150" = load i32, i32* %".149"
  store i32 %".150", i32* %"j"
  br label %"cond.4"
quit.4:
  br label %"cond.5"
cond.5:
  %".154" = load i8*, i8** %".6"
  %".155" = load i8, i8* %".154"
  %".156" = bitcast i8* %".154" to [0 x i8]*
  %".157" = load i32, i32* %"j"
  %".158" = add i32 %".157", 1
  %".159" = getelementptr [0 x i8], [0 x i8]* %".156", i32 0, i32 %".158"
  %".160" = load i8, i8* %".159"
  %".161" = load i8*, i8** %".3"
  %".162" = load i8, i8* %".161"
  %".163" = bitcast i8* %".161" to [0 x i8]*
  %".164" = load i32, i32* %"i"
  %".165" = getelementptr [0 x i8], [0 x i8]* %".163", i32 0, i32 %".164"
  %".166" = load i8, i8* %".165"
  %".167" = icmp eq i8 %".166", %".160"
  br i1 %".167", label %"stat.5", label %"quit.5"
stat.5:
  %".169" = load i32, i32* %"j"
  %".170" = load i32, i32* %"j"
  %".171" = add i32 %".170", 1
  store i32 %".171", i32* %"j"
  br label %"quit.5"
quit.5:
  br label %"cond.6"
cond.6:
  %".175" = load i32, i32* %"keyLen"
  %".176" = load i32, i32* %"j"
  %".177" = add i32 %".176", 1
  %".178" = icmp eq i32 %".177", %".175"
  br i1 %".178", label %"stat.6", label %"quit.6"
stat.6:
  %".180" = load i32, i32* %"matched"
  store i32 1, i32* %"matched"
  %".182" = load i32, i32* %"k"
  %".183" = load i32, i32* %"j"
  %".184" = load i32, i32* %"i"
  %".185" = sub i32 %".184", %".183"
  store i32 %".185", i32* %"k"
  %".187" = alloca [4 x i8]
  store [4 x i8] [i8 37, i8 100, i8 32, i8 0], [4 x i8]* %".187"
  %".189" = bitcast [4 x i8]* %".187" to i8*
  %".190" = load i32, i32* %"k"
  %".191" = call i32 (i8*, ...) @"printf"(i8* %".189", i32 %".190")
  br label %"quit.6"
quit.6:
  %".193" = load i32, i32* %"i"
  %".194" = load i32, i32* %"i"
  %".195" = add i32 %".194", 1
  store i32 %".195", i32* %"i"
  br label %"cond.3"
cond.7:
  %".199" = load i32, i32* %"matched"
  %".200" = icmp eq i32 %".199", 0
  br i1 %".200", label %"stat.7", label %"quit.7"
stat.7:
  %".202" = alloca [7 x i8]
  store [7 x i8] [i8 70, i8 97, i8 108, i8 115, i8 101, i8 10, i8 0], [7 x i8]* %".202"
  %".204" = bitcast [7 x i8]* %".202" to i8*
  %".205" = call i32 (i8*, ...) @"printf"(i8* %".204")
  br label %"quit.7"
quit.7:
  ret i32 0
}
