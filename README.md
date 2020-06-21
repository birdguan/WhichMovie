# WHICH FILM，探寻您与一部电影的缘分
![](imgs/show.png)

# Dependencies
- [LitePal 3.1.1](https://github.com/LitePalFramework/LitePal)
- [com.roughike:bottom-bar:2.3.1](com.roughike:bottom-bar:2.3.1)
- OkHttp3
- Picasso
- [CircleButton](https://github.com/markushi/android-circlebutton)

# 功能
## 翻一部
由于豆瓣电影条目id不连续，无法随机选取，因此爬取了尽可能多的条目保存在阿里云服务器并用python搭建了一个简单的API服务器以返回确定的条目id。  
随机API示例：[http://39.101.204.206:18080/?index=10086](http://39.101.204.206:18080/?index=10086)

# Acknowlege
## 豆瓣api
看到有不少人说豆瓣API无法申请了，其实Github还有[豆瓣API备份文档](http://www.doubanapi.com/movie.html#subject)，  
还给出了apikey:0df993c66c0c636e29ecbb5344252a4a
### 调用格式
https://api.douban.com/v2/movie/subject/**id**?apikey=0df993c66c0c636e29ecbb5344252a4a  
其中**id**需要自己替换。

由于豆瓣电影条目id是不连续的，因此爬取了可访问的id，共计138234条。