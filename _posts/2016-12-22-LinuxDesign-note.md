---
layout: post
title: Linux 程序设计(5)
categories: LinuxDesign
description: LinuxDesign
keywords: LinuxDesign
---



# Linux 程序设计(5)

## 接上次基本命令

**find** 很重要

寻找所有的 .C 文件

find  . -name '*.c'  (.—> 当前文件夹)

寻找所有的 .C 文件和.H 文件  （-o = or）

find . -name '*.c' -o  .  -name '\*.H'



**grep** 很重要 正则表达式的匹配 寻找字符串

grep 'tree' -n (-n = 显示行号)

find 重定向 xarg grep



**sed** 用来替换文本

sed 's/…a../..b../g'

将 a 替换成 b 。a，b均为正则表达式

1. s = 替换
2. g = 替换所有



**file**  显示文件类型

**head** 当文件很大时，可以只显示开头的一段   head -100 a.txt 显示前 100 行

**tail**  当文件很大时，可以只显示最后的一段



## 重定向

更改标准输出输入流到别的地方.

任意命令 + "<"/">"/">>"/"2>" 



#### 机制

操作系统内核会提供 stdin，stdout，stderr 的实现

对应的文件描述符        0           1               2

程序调用输出时，（不论java，c...）都会调用 1 号文件描述符，实现输出

而重定向就是改动1号文件描述符，将输出定位到别的地方

">" 是不重载 stderr ，也就是说，错误信息是不会被写入文件，而是照样显示在控制台

"1>": 对 stdout 重定向

"2>" : 对 stderr 重定向

">>": 将输出追加到文件末尾

如果既想重定向 1，也想重定向 2，可以：

1. &> a.txt
2. "> a.txt 2>b.txt"
3. "> a.txt 2>&1"



### 管道

前一个进程的输出作为后一个进程的输入 。也就是 前一个进程的输出 被**重定向**为 后一个命令的标准输入

wc -l a.txt

wc -l <a.txt 虽然结果一样，但是机理完全不一样



### 环境变量

显示环境变量：

echo：echo $PATH

env: 显示所有的环境变量

set: 修改环境变量 ，往往省略

PATH=$PATH:.

export PATH （将PATH在 shell 里输出到系统中，否则只会在当前终端有效）

***注意** export 只会影响当前进程的子进程