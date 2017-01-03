---
layout: post
title: Linux 系统基础 (6)
categories: LinuxBasic
description: LinuxBasic
keywords: LinuxBasic
---



# Linux 系统基础(6)

> ## Linux 常用命令

### 文本显示和处理

**cat**：显示文件内容   -n 标注行号   -b 标注行号（不标空白行）

**less：** 可以回退显示

**more：** 分页

**head:** 查看文件前x行 head -x filename

**tail:**    查看文件最后x行 tail -x filename  

​	    -f ：动态的显示

**sort：**  显示的时候把文件的内容排序（不改变文件内容）

-r 倒序

**uniq：** 查看文件中重复的或者不重复的内容

**cut：** 从文件每行中显示选定的字符

**comm:**比较两个文件

**diff：**  比较多个文件 可以生成 patch 文件

**patch：**  给旧文件打补丁，使其变成新文件



### 查找

**grep：** 查找文件内的文件内容   grep 'keyword'  filename

**find:**  要指定查找文件的位置 find /etc -name http.conf   可以按很多条件搜索

**locate:** 查找文件，比 find 搜索快，利用文件系统的索引，会对**整个系统**进行搜索 ，索引文件定期更新

**rpm：**  软件管理



**hosts文件** ：查 DNS 之前先查 hosts 文件

**dmesg:** 把最近的系统日志显示出来,显示启动的每一步的日志

**date:**  显示时间，一般用于文件名里

**注意 图形界面下的终端叫 pts/0,/1….不是 tty**

**rsync :**差异备份 类似于 git



