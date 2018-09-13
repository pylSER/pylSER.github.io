---
layout: post
title: Search Engine 1
categories: SearchEngine
description: Search Engine
keywords: Search Engine
---

# Search Engine

AKA: Information retrieval (IR), Document retrieval, Text retrieval

### Data Kinds

- unformatted or unstructured data
- semi-sturctrued
- non-surctured 

## Why IR Difficult?

- size of WEB is doubling
- unstructured data is hard to read. Synatics --> Semantics --> real world knowledge
- Diversified user 
- ...



## Boolean and Vector Space Retrival Models

### Boolean Model

“ 非结构化数据”(unstructured data) 指的是那些没有清晰和明显语义结构的数据，计算机不易处理这类数据



线性扫描可以用，但是不好：

- 线性扫描可以进行检索，但是效率不高，尤其当文本集很大的时候
- 线性扫描不灵活，用户希望可以输入更灵活的查询条件
- 线性扫描只能得到结果，不能给结果排序



非线性的扫描方式：

首先建立索引：

- 词项—文档关联矩阵：记录了每一个词项是否在某一个文本中出现

将一行作为一个矩阵，然后AND位操作，就得到了搜索结果 



一些术语：

Information Need：信息需求 用户想查找的信息主题

Query：查询 有用户输入，不同的查询可能代表着同样的信息需求

Precision：返回的结果中真正和信息需求相关的文档所占的百分比

Recall：所有和信息需求真正相关的文档中被检索系统返回的百分比



倒排索引

词项—文档关联矩阵 将过于庞大，而且大部分的cell 都会是0. 这样的话直接记录1的位置会大幅度压缩空间。

1. 构建词典和倒排记录表

   词项词典 --> 倒排记录表(记录出现倒排的所有文章)

2. 处理查询

   A AND B : 求A的倒排记录表A‘  求B的倒排记录表B‘   求A’和B‘的交集

   求交集可以使用2个指针 （较小的向前移）这要求倒排记录表必须是有序的

3. 优化

   可以通过存储额外的信息，如每一个倒排表的长度，来优化 AND 和 OR 运算





documents are recorded as a set of keywords(bags of words == BOW)

Query 是 关键词的布尔运算 

Need a paralegal to perform a search. User usually does not know how to use search engine.

