---
layout: post
title: Machine Learning
categories: Machine Learning
description: Machine Learning
keywords: Machine Learning
---



# Machine Learning

> ## 大规模机器学习

**低偏差算法+大数据=好结果**

当样本容量很小时：算法的 方差 很高，意味着 **过拟合** 这是需要再添加数据来使得 Jcv 慢慢 趋近于 Jtrain,这是就可以使用更多的数据了



> ## 随机梯度下降算法 ( stochastic gradient descent )

在数据量很大的情况下，梯度下降算法会很慢，这里介绍随机梯度下降算法。

原本的算法：（批量梯度）

1. 读入数据（可能是几亿个）—>对每一个数据求导 —>求和 —完成一次迭代
2. 重复1，直到找到最小的 cost function
3. 结果，会收敛

现在的算法：

1. 读入一个数据，计算一次 theta 
2. 重复1，直到 theta 读完，具体见本子
3. 结果，不会收敛，但是会在最小值附近徘徊



## 小批量梯度下降算法

介于批量和随机之间：

批量是每一次要使用全部数据，而随机是每一次使用一个数据

小批量是每一次使用 [1,m] 个数据

在代码中使用 良好的向量化之后，小批量的效率甚至高于随机，因为

对于外层循环而言，小批量的次数是m/b，随机是m



## 确保收敛

可以使用图像

可以随时间变化来减小学习速率，以确保精确