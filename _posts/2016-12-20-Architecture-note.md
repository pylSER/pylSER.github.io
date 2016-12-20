---
layout: post
title: 体系结构(4)
categories: Architecture
description: 体系结构
keywords: 体系结构
---



# 体系结构

## 决策

决策相当重要！

设计决策往往只有结果，应该给出决策过程。



> ## 第四章 体系结构描述

### 描述方法

- BOX-LINE （简单易理解）
- 形式化语言 （过于严谨）
- UML



### BOX-LINE

缺点：

- 表述模糊
- 难以检测一致性
- 细节不完备
- 代码和设计无法追踪



### 形式化语言

优点：

- 严谨
- 人可读，机器可读
- 达到真正意义上的高层，与代码无关
- 完备，一致，可以做性能测试

缺点：

- 目前形式化语言不普遍，对其功能定义模糊
- 企业较少使用，多用于学术界
- 很多形式化语言只适用于某一种分析



### UML

多数 UML 和 4+1 视图一起使用

优点：

- 可拓展

缺点：

- 通过定制后的UML会使读者模糊



## 4+1 视图

4: 设计师设计 4 个视图

- Logic View  功能的分配
- Development View 决定开发包的数目，和内容，然后分工
- Process View
- Physical View

1: 还有一个 View 从需求继承 （一般是质量场景）



### Logical View

使用 Component，Connector，Configuration

考虑功能，**质量，约束**是怎么分解的？

图虽然是类图但不再是类

port



制作出板（component）和螺丝（connector）

板子和板子之间必须使用螺丝连接

状态协议机

### Develpoment View

#### 重点是 Programmer

分工，最好一个 team 分一个

结合现实分工

物理包／子系统

### Process View

明确最终有多少个程序，进程

将代码分割成不同的运行单位（可以最大分割～最小分割）

使用 UML 的 ActiveClass 表示进程



### Physical View

考虑硬件资源的使用 



### Scenario

是需求给出的 非功能需求





### Logic—> Development

### Logic —> Process

### Development Process—>Physical

#### 可以根据实际情况动态添加和删除 View

