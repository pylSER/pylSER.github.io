---
layout: post
title: Linux 程序设计(7)
categories: LinuxDesign
description: LinuxDesign
keywords: LinuxDesign
---

# Linux 程序设计

> ## Shell 编程 2

- until 语句

  判断条件满足，循环退出，其他形式和 while 一样

- select 语句  生成菜单列表，需要在内部设置一个退出，否则会一直让你选择



### 命令表

分号串联：挨个执行

AND &&：串联执行

OR|| ：一旦成功就不继续执行



### 语句块

#### 函数

```shell
func() // func是函数名
{
	
  $1,$2 //获得参数
  statements
}
函数中的变量没有声明局部的话(local)，就是全局的

函数的调用：
func p1 p2 (p1,p2 是实参)

如果函数中没有 return。那么，会返回最后一条语句的返回值

```



- unset 销毁变量
- set 设置参数
- trap 自定义中断动作  trap "command"  INT



### 捕获命令的输出

$(command)     \`command\`     这样可以把结果存到变量里



### 算术扩展

结果=$((算术表达式))



### 参数扩展

eg. 批处理创建文件 替换文件名— 将 .cpp .c .Cpp   替换为 xx.o

i=0

${i} 

![屏幕快照 2016-12-30 下午3.30.50](/images/posts/linux/paramtable.png)



### 即时文档

关于： <<

在 Shell 脚本中向一条命令传送数据

command << starttag

\> line1

\>line2

\>starttag

其中 line1 和 line2 将作为输入

line1 line2 叫即时文档

