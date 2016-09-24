---
layout: post
title: 字幕组论文总结2
categories: HCI
description: 字幕组论文总结
keywords: HCI Caption
---


# 总结
## 「Crowd Caption Correction (CCC)」

### ABSTRACT
   目前字幕无处不在。当开远程会议的时候，字幕往往会出现错误的情况。
   __The Crowd Caption Correction (CCC)__ 方法就可以实时允许与会者或第三方修改错误的字幕。同时，__CCC__ 也会记录一份所有字幕的副本，供以后参考。

### INTRODUCTION
   在开远程会议时，字幕对于听力障碍人士来说尤为重要，然而目前字幕常常出错，导致听力障碍人士对会议不能全面了解。
   导致字幕出错的原因有很多: 音频质量不高、多人同时说话、语速过快、字幕提供者不了解术语等都会使字幕质量低下。
   在直播的环境下，字幕提供者也来不及对字幕仔细检查。
   有些方法已经正在努力对字幕进行纠正，比如，同时采用多个字幕提供者，比较他们的字幕质量，选取他们的字幕。

### CROWD CAPTION CORRECTION
__CCC__ 允许在会议期间通过认证方实时修改错误字幕。认证方(可以是会议参与者的部份或全体) 可以登录，并修改当前的字幕。当然，被修改的字幕也可以被修改。
修改后的字幕以一种不同的方式展现出来(字体，大小，高亮等形式)。 __CCC__ 方法同时也解决了修改的并发冲突问题。
> CCC 修改方法的具体步骤:
1. 字幕出现语义不明的一条语句，如图:
![figure1](/images/posts/hci/caption1.png)
听力障碍人士此时无法判断是字幕出错还是自己的理解有问题。
2. 认证方发现该字幕有错误，点击该处字幕，此时字幕变成一个可编辑的文本框。如图:
![figure1](/images/posts/hci/caption2.png)
3. 认证方修改字幕，按回车完成修改，如图:
![figure1](/images/posts/hci/caption3.png)
4. 最后，所有与会者将看到如下字幕，红色部份表示此处被修改过，如图:
![figure1](/images/posts/hci/caption4.png)

### OPEN ACCESS TOOL TRAY SYSTEM (OATTS)
(OATTS) 可以帮助与会者通过插件的方式提供其他功能。

### CONCLUSION
__CCC__ 是一个能够实时修改错误字幕的方法，它可以独立使用，也可以作为 __OATTS__ 的一个插件使用。
