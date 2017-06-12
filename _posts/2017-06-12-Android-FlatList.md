---
layout: post
title: React Native FlatList 性能调优
categories: Android
description: 
keywords: Android
---

# React Native FlatList 性能调优

## 背景

FlatList 是 Facebook 团队在 2017 年新推出的一个 List 组件，旨在替代原来的 ListView+DataSource，因为原来的列表显示方法存在大内存占用并存在一些 bug. FlatList 给我们提供了更简单，更灵活的方式来创建一个 List.

## 遇到的问题

在移动教学课程系统中，有一个需求是显示班级的学生。既然是显示的列表，那么我选择了使用上述的 FlatList 作为列表组件。在应对普通需求时，FlatList 表现正常，能够及时的进行渲染，并与用户交互。但是如果学生变多(超过50), FlatList 的性能会急剧下降，主要会出现：

- 加载时间过长， 在 FlatList 加载的时候用户看不到元素
- 用户下滑加载好了的 FlatList 列表至最底部时，FlatList 会继续加载未显示的数据，此时的加载的时间过长，会出现用户等待，此时 UI 交互会卡住，ListItem 未渲染但可以下滑，用户继续下滑会看见有的 ListItem 被渲染出来而有的没有。
- 直接渲染 300 个 ListItem 需要大概1分钟，无法接受

虽然 FlatList 对外宣称渲染性能变强，但还是没有达到我们的预期。不过原因之一是每一个 ListItem 中存在一定的复杂性，并非简单的 Text, 此外 Separator (分割线) 由用户自己定义，也存在一定的复杂性。



## 我的解决方案

要解决性能问题我的想法是从两方面入手：

1. 解决数据加载时时间过长的问题
2. 如果真的出现加载时间过长，应该有一个让用户等待并得到反馈的机制

### 解决数据加载时间过长

从需求分析，教师查看学生列表往往不是为了查看所有学生，更多的是寻找一个学生，那么就没有必要去极力的完整的显示数据，在教师寻找的过程中我们可以异步加载数据。而且，理想的情况是：当教师找到他想要的学生时，就先暂停异步加载，不再渲染，保证用户的交互顺畅。

根据以上分析，具体到代码的实现有：

1. 解决一次只加载少量数据，其他数据异步加载
2. 确定异步加载的时间
3. 实现一个搜索功能，尽量不要让用户尝试加载全部数据



对于 1 的实现：代码附有注释，过一遍是没有问题的

````js

var initNumber=12;
// 这里选择一次显示的个数为12
// 原因是 12 个 item 一般会占满手机的整个屏幕，不能出现 item 未占满屏幕，而还有数据未加载，让用户产生数据已经完全加载的错觉
// 一次渲染12个item的时间大约为2s，在用户的接受范围内

// 获取数据
getData(){
  
  //如果获得的数据长度是0，通知UI显示相应界面
    if(this.props.navigation.state.params.datalen==0){
        this.handleEmpty();
        this.stopLoading();
        return;
    }
  
  
    var that=this;
  
  // 用 fetch 异步获得总数据
    var url='http://115.29.184.56:8090/api/group/'+this.props.navigation.state.params.gid+'/students';
    this.timeout(5000, fetch(url)).then( (response)=> {  //回调函数
      response.json().then(function(data) { // 回调函数的回调函数
                  var datalen=data.length;
				
        // 再次验证获得的数据，防止数据在fetch前又被更新
                  if(datalen==0){
                    that.handleEmpty();
                  }else {
                    that.handleNOTEmpty();

                    var JSONlen=0;
                    
                    //应 Flatlist 要求，每一个 json 项里要有 key 这个属性，
                    // 将 id 赋为 key
                    for(var id in data){
                      data[JSONlen]['key']=data[JSONlen].id;
                      JSONlen+=1;
                    }
                    
                    //判断在第一次取总数据后还要不要加载，
                    //如果数据不够12就不用再加载了，减少加载次数
                    var showdata=[];
                    var showlen=0;
                    if(datalen<=initNumber){
                      showlen=datalen;
                      that.setState({noNeedLoad: true});
                    }else {
                      showlen=initNumber;
                      that.setState({noNeedLoad: false});
                    }


                    // 构造要显示的数据集合
                    for(var i=0;i<showlen;i++){
                      showdata.push(data[i]);
                    }
                    console.log('THIS');
                    console.log(showdata);

                    that.setState({listShowData: showdata});
                    that.setState({listdata: data});

                    that.setState({currentIndex:1});
                  }

                  console.log(data);
                  that.stopLoading();

            }).catch(function(error){
              that.doNetWorkError();
            })
  }).catch(function(error) {
    that.doNetWorkError();
    })
  },
````

对于2:

````javascript
// Flatlist 组件  
<FlatList
                        ListFooterComponent={this.renderFooter}
                        ItemSeparatorComponent={this.renderSeparator}
                        refreshing={this.state.refreshing}
                        onRefresh={this.doPullRefresh}
                        onEndReached={this.loadMore}
                        initialNumToRender={initNumber}
                        onEndReachedThreshold={0.5}//确定加载数据执行时间，0.5意味着：当用户浏览过当前list的50%时，执行 onEndReached 函数 (loadMore)
                     
                        removeClippedSubviews={false}
                        data={this.state.listShowData}
                        renderItem={({item}) => <ListItem onPress={this.goToStuInfo} alldata={item} id={item.key} number={item.number} avatar={item.avatar} name={item.name} />}
                      />
                          
                          
// loadMore 函数 
 loadMore(){
                          
    // 先看当前数据是不是还要加载                      
    if(this.state.noNeedLoad){
      console.log('no need load');
      return;
    }else {
      // 如果需要加载：
      console.log('load more');
      var alldata=this.state.listdata;

      var showdata=this.state.listShowData;

      
      
      // 获取对应的数据
      var startPos=this.state.currentIndex*initNumber;

      var duration=0;
      if((startPos+initNumber)<=alldata.length){
        duration=startPos+initNumber;
      }else {
        duration=alldata.length;
        this.setState({noNeedLoad: true});
        this.endFooter();
      }
	
      // 把获得的数据加到要显示的集合里
      for (var i = startPos; i <duration ; i++) {
        showdata.push(alldata[i]);
      }

      //数据偏移量加一，下次从这开始取
      this.setState({currentIndex:(this.state.currentIndex+1) });
      this.setState({listShowData:showdata });
    }
  },
````



对于 3 ：

当用户点击搜索按钮后，跳转至搜索界面，和上面一样，还是会有一个搜索结果的 FlatList，实现和上面类似，不能简化，因为我们无法确保搜索结果的大小，简化后性能上还是会出现问题。



### 如果真的出现加载时间过长，应该有一个让用户等待并得到反馈的机制

虽然现在加载数据的时间已经从1分钟控制到了多个2s的异步加载，但用户还是存在等待行为，现在给用户等待加上 UI的反馈。

1. 用户点入列表界面时，首次加载数据时的等待
2. 用户滑至列表底部，继续加载数据时的等待



对于1的实现：代码附有注释，过一遍是没有问题的

````javascript
getInitialState(){
    return{
      isNetworkNotOK:false,  // 处理网络异常，此处先不细讲
      isLoading:true,   // 进入界面后 isLoading 的 state 先被设为 true
      refreshing:false,
      isEmpty:false,
      listdata:'',
      footerText:'加载中...',
      listShowData:'',
      currentIndex:0,
      noNeedLoad:false,
    }
  },
 
    //等待 UI -- render 方法中：
      {this.state.isLoading &&
          <ActivityIndicator color="#025ca6" size={50}  style={styles.indicator} />
          }

         {this.state.isLoading &&
          <Text style={styles.loadingText}>获取数据中...</Text>
          }

//根据 isLoading 这个 state 来判断是否渲染这两个元素
````

对于2: 

2中是用户要获得更多的数据，所以我将等待UI放在列表最下方，也就是 FlatList 的 footer：

````javascript
// 还是那个 FlatList
<FlatList
                        ListFooterComponent={this.renderFooter}  //这个 footer 会显示等待或加载完成
                        ItemSeparatorComponent={this.renderSeparator}
                        refreshing={this.state.refreshing}
                        onRefresh={this.doPullRefresh}
                        onEndReached={this.loadMore}
                        initialNumToRender={initNumber}
                        onEndReachedThreshold={0.5}
                        removeClippedSubviews={false}
                        data={this.state.listShowData}
                        renderItem={({item}) => <ListItem onPress={this.goToStuInfo} alldata={item} id={item.key} number={item.number} avatar={item.avatar} name={item.name} />}
                      />
 // footer 具体实现：
renderFooter(){
                          //没有数据不显示 footer
    if(this.props.navigation.state.params.datalen==0){
      return (

        <Text>

        </Text>

      );
      
      
// 数据少于12个，不需要加载时：
    }else if(this.props.navigation.state.params.datalen<=initNumber){

      // footer 显示学生个数，表示加载完毕
        return (
          <Text style={{textAlign:'center',fontSize:15,marginBottom:5}}>
            共 {this.props.navigation.state.params.datalen} 个学生

          </Text>
        );
      // 数据还要加载，显示“加载中”
    }else if (this.props.navigation.state.params.datalen>initNumber) {
      return (
        <Text style={{textAlign:'center',fontSize:15,marginBottom:5}}>
          {this.state.footerText}
        </Text>
      );
    }

  },
    // endfooter 会在上面的 loadMore 中被调用，一旦 loadMore 发现加载完毕，就会调用loadMore
    // footer 显示学生个数，表示加载完毕
  endFooter(){
    var text='共 '+this.props.navigation.state.params.datalen+' 个学生';
    this.setState({footerText:text });
  },                          
````



最后效果截图：(以搜索为例)!

![屏幕快照 2017-04-07 下午3.49.52](/images/posts/android/result.jpg)