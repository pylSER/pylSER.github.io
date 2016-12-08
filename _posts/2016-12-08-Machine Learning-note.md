---
layout: post
title: Machine Learning(2)
categories: Machine Learning
description: Machine Learning
keywords: Machine Learning
---

# Machine Learning (2)

> ## Online Learning

在线学习机制

从源源不断的用户数据流中，进行机器学习



> ## 映射约减 Map Reduce

分布式计算，具体见本子



> ## 照片 OCR 技术

识别照片中的文字信息

**机器学习流水线**

1. 确定图片中的文字的位置
2. 字符划分
3. 识别这些字符



### 滑动窗分类器 （sliding windows）

行人检测：行人的矩形的长宽比是相似的

找行人和非行人的图片数据作为学习数据

对于目标图片，取行人矩形大小的一个框，从左上角框住目标图片的一部分，截取，用算法判断是不是行人。将这个框向右滑动，继续检测。滑动的长度是自己决定的



### 对已得数据再加工（加有意义的噪音）可以获得更多数据

### 但加随机噪声没有用



> ## 上限分析 Ceiling Analysis

 开发流水线时，确定在哪个组件上主要投入精力。

使用桩组件来确定组件的经过这个组件之后，系统的学习准确率

这样可以看出每个组件对提升学习准确率的能力











