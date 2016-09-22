---
layout: page
title: About
description: MedCare Station
keywords: Pei Yulin
comments: true
menu: 关于
permalink: /about/
---

## 联系

* GitHub：[@pylSER](https://github.com/pylSER)
* 博客：[{{ site.title }}]({{ site.url }})


## Skill Keywords

#### Software Engineer Keywords
<div class="btn-inline">
    {% for keyword in site.skill_software_keywords %}
    <button class="btn btn-outline" type="button">{{ keyword }}</button>
    {% endfor %}
</div>

#### Mobile Developer Keywords
<div class="btn-inline">
    {% for keyword in site.skill_mobile_app_keywords %}
    <button class="btn btn-outline" type="button">{{ keyword }}</button>
    {% endfor %}
</div>

#### *nix Developer Keywords
<div class="btn-inline">
    {% for keyword in site.skill_windows_keywords %}
    <button class="btn btn-outline" type="button">{{ keyword }}</button>
    {% endfor %}
</div>
