---
layout: post
title: 体系结构(2)
categories: Architecture
description: 体系结构
keywords: 体系结构
---

# 体系结构(2)

> ## **第二章 — 软件设计的层次**

## 低层设计

要验证代码的**正确性**

3 种控制结构

代码块->函数

数据结构的设计

代码设计

问题：程序设计语言提供的类型与语句有限

方法：基于基础类型和语句，建立程序设计需要的各种类型与算法

类型

- 通用：使用一个Primitive类型 建立一个新的Type？
- 复杂：数据结构

算法

- 正确性：结构化编程理论
- 其他质量：软件构造技术
- 复杂算法？：《计算机程序设计艺术》

逻辑：类型（概念抽象）与用法

              算法逻辑

物理：基于编程语言的类型处理及使用保护

              控制结构

## 中层设计

当函数变多时，需要对函数再抽象—> 模块   复杂度变为  ：num(模块)*num(函数)

模块划分：将系统分为简单的片段（尽量独立，有利于理解，复用，修改）

本来是希望给模块定义接口，相互调用，结果没实现

提高可修改性：**信息隐藏**

- 模块+内聚／耦合 标准=模块化
- 信息隐藏=模块化+可修改性
- 模块化+信息隐藏+（ADT，封装，继承，多态）=面向对象



面向对象的设计原则(讲过了)

设计模式

各种复杂的功能都可以做了

## 高层设计（体系结构）

处理复杂质量，性能，解决编程解决不了的问题（硬盘，CPU，内存 等问题）

重视系统的总体组织，怎么分模块

主要了考虑的是质量，不是功能！功能给中层设计。

对于非功能的切割问题？



