---
layout: post
title: 体系结构 — 风格
categories: Architecture
description: 体系结构
keywords: 体系结构
---

# 体系结构

> ## 第六章— 风格

实验中 爬取用管道过滤器风格（可能会用到的：隐式调用，共享）

**connector 和 component 的区别是 是否承担需求任务**

### 你的 Connector 会相当的影响系统的质量！



### - 模块设计可能会用到的风格

#### 模块之间用程序调用

1. 主程序子路径  数据和功能绑在一起，影响范围很大，可能会导致连锁反应

   按功能分解，从上往下调用，单线程，逻辑上：严格树状结构

   **优点**：过程清晰，正确性很高

   **缺点**：修改性差，耦合过高

2. 面向对象   和主程序子路径差不多 可复用性变好一些

   网状结构

   **优点：**数据封装，隐藏

   **缺点：** 可能会产生副作用，正确性不太好

3. 分层



#### 模块之间不用程序调用

1. 隐式调用 ： 使用事件的方式通知对方，都还不错，可测试性有问题
2. 数据流水线
3. 数据共享


Pipe & Fliter  ：可交互有问题；不允许sharedata，占空间；时间好，可并发



### 分层风格

优点： 1. 易修改 2. 易复用

缺点： 1. 有的系统分不了层  2. 性能比较低（除非跨层调用）

和 **主程序子路径** 的区分：主程序和子路径是功能分解，一个分解可能会调用所有层

分层是全局性的抽象



### MVC

优点：支持多 View，易改变的 View

缺点：可修改性不好

和**分层**的区别：关注点不同，分层是全局性的抽象，MVC 注重View的可变

由 WEB 普及



### SSH

Struts+Spring+Hibernate

分层中使用 MVC



### Pipe & Filter （将在实验里大量使用）

每一个 Filter 必须独立，不能依赖上一个 Filter，就和流水线一样   e.g bash 的管道

 也叫：数据总线／消息总线

优点：易于理解，支持复用，并发，可修改

缺点：不适用交互性强的系统（使用批处理解决：Filter获得所有数据后再往下传（数据要标记最后的））

Pipe 才是整个系统的关键，用 Pipe 隔离 Filter ，避免 Filter sharedata。 Filter 一定不知道数据在物理上是怎么来的。

Filter 中，不要假设数据是对的，要验证



### 隐式调用  IIS

p —调用 (使用事件)—> q

p 不知道调用谁  registEvent

q不知道被谁调用 registListener

调用由**事件**来管理

1. 不存在直接调用
2. 不能假设 Event 一定会被 Listen 到
3. 不能假设 Event 的监听顺序

适用于调用比较少的两个子系统之间，多了的话事件不好跟踪

优点：复用性好

缺点：无法保证正确性，难以调试。无法知道事件究竟是否被正确处理

Router 是整个系统的关键



### 仓库（黑板）风格

以数据库为主

#### PULL - 仓库

Agent 从仓库 PULL 数据

#### PUSH - 黑板

仓库+Monitor（注册，控制）

黑板将注册 Agent ，黑板会通知感兴趣的 Agent

用于以**信息交换**为主的环境

黑板将 sharedata

优点：集中控制数据。易于修改程序

缺点：必须实现一个数据模型；黑板将成为一个瓶颈；数据的演化很难









