---
layout: post
title: React Native 工作机理
categories: Android
description: 
keywords: Android
---



# React Native 工作机理

React Native 听起来就很酷。所以这次的 APP 开发我决定用 React Native 来尝试，考虑到上学期用完全的 React 开发过网站，所以感觉上手应该也不难～

> React Native 不是 移动 web app，也不是 html5 app 或者 hybrid app。
>
> 它就是真正的和原生应用一样的 app。**UI = f(data)**					 — React Native Facebook 开发团队

## 1. 具体开发流程

想要了解 RN 是怎么工作的，至少要知道 RN 的开发流程是什么吧：

安装和使用 npm ， nodejs，react native 在此省略

1. 创建 RN 项目:  `react-native init 项目名`

2. 编写 js 代码

3. 编译并运行 (这一步需要一个开启 USB 调试的安卓真机或模拟机) ：

   3.1 将设备连接电脑（虚拟机当然不用）

   3.2 `react-native run-android ` 等待编译完成，安装

   3.3 app 安装完成，自动运行

4. debug 热更新（React Native 最大的优势(我认为)）

   4.0  确保手机和开发电脑在一个局域网内，或手机可以访问开发机器(公网 IP)

   ​	可以点击菜单键或摇晃手机，在 Dev Settings 中修改开发机器的 IP

   4.1  修改完了代码，打算试试看效果

   4.2  进入 app，点击菜单键或摇晃手机，点击 Reload，或 连按 2 下 R 键

   4.3 查看 debug 后的 app

## 2. 怎么实现的？

### 1. 编译和运行

#### 1.1 整体架构

如图：

关于 Virtual DOM：

![rn-arch](/images/posts/android/rn-arch.jpeg)

从上面这张图可以看出，React Native 使用 JSX 生成 一个 Virtual DOM, 原生代码会根据 Virtual DOM 来进行相应的渲染。具体从上往下来说：

1. 编写好 JSX 文件，将其编译成 JS 文件
2. js 文件映射至 Virtual DOM，并通过 Bridge 调用 native 方法
3. 处理回调结果

- 先不说映射，js 怎么在安卓环境下执行？

—  要让 app 运行必须运行这些编译好的js，在 iOS 中，JavascriptCore 可以提供 Runtime。 安卓使用了开源的动态链接库 jsc.so. 可以在  `node_modules` 里找到这个库。

- 为什么要映射成 Virtual DOM？

— 个人认为原因有两点：第一是为了实现跨平台，这就好像在 Java 代码和 Binary Code 之间加了一层 Virtual Machine 一样，Virtual Machine 将 Java 代码转换为针对各个操作系统的二进制码；Virtual DOM 将组件转化为 native View 的渲染。RN 只需对不同平台开发不同的 Virtual DOM 和 Bridge 即可使原有的代码兼容新的平台。第二是提高了渲染的效率，React Native 实现了一个高效的 DOM 间的 diff 算法，它能够快速比对出目前内存中的 DOM 和准备更新的 DOM 的不同，从而只渲染需要更新的部分。

- js 和 native 方法之间的关系是什么？

— JS 像整个 app 的服务端，native 这边更像一个客户端。

为了理解这句话，我们举一个 RN App 启动的例子：

#### 1.2 启动流程

//以下代码均是 React Native 0.43.4 的源代码

和其他的安卓应用一样，React Native 的程序入口也是 MainActivity.java:

```jsx
package com.awesomeproject;

import com.facebook.react.ReactActivity;

public class MainActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "AwesomeProject";
      //这里返回的项目名称，这个项目名称要和 js 导出的项目名称一致，才能对接
    }
}
```

代码相当简单，看来 React Native 已经封装好了一个 Activity，ReactActivity.java:

```jsx
package com.facebook.react;
//省略 import.....

/**
 * Base Activity for React Native applications.
 */
public abstract class ReactActivity extends Activity
    implements DefaultHardwareBackBtnHandler, PermissionAwareActivity {

      
      
  private final ReactActivityDelegate mDelegate; 
      // 我们发现这个 Activity 又通过一个委托类来完成额外的任务

  protected ReactActivity() {
    mDelegate = createReactActivityDelegate();
  }

  protected @Nullable String getMainComponentName() {
    return null;
  }

  /**
   * Called at construction time, override if you have a custom delegate implementation.
   */
  protected ReactActivityDelegate createReactActivityDelegate() {
    return new ReactActivityDelegate(this, getMainComponentName());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mDelegate.onCreate(savedInstanceState);
    //MainActivity 创建完成后react native 委托类来实现真正功能
  }
//.....省略其他方法
```

ReactActivityDelegate.java:

```jsx
// Copyright 2004-present Facebook. All Rights Reserved.

package com.facebook.react;
//省略 import.....
public class ReactActivityDelegate {

  private final int REQUEST_OVERLAY_PERMISSION_CODE = 1111;
  private static final String REDBOX_PERMISSION_GRANTED_MESSAGE =
    "Overlay permissions have been granted.";
  private static final String REDBOX_PERMISSION_MESSAGE =
    "Overlay permissions needs to be granted in order for react native apps to run in dev mode";

  private final @Nullable Activity mActivity;
  private final @Nullable FragmentActivity mFragmentActivity;
  private final @Nullable String mMainComponentName;

// ReactRootView 是整个app的关键
  private @Nullable ReactRootView mReactRootView;
  private @Nullable DoubleTapReloadRecognizer mDoubleTapReloadRecognizer;
  private @Nullable PermissionListener mPermissionListener;
  private @Nullable Callback mPermissionsCallback;

  public ReactActivityDelegate(Activity activity, @Nullable String mainComponentName) {
    mActivity = activity;
    mMainComponentName = mainComponentName;
    mFragmentActivity = null;
  }

  public ReactActivityDelegate(
    FragmentActivity fragmentActivity,
    @Nullable String mainComponentName) {
    mFragmentActivity = fragmentActivity;
    mMainComponentName = mainComponentName;
    mActivity = null;
  }
  protected @Nullable Bundle getLaunchOptions() {
    return null;
  }

  protected ReactRootView createRootView() {
    return new ReactRootView(getContext());
    //getContext 方法获得的是 MainActivity 对象
  }

//省略其他方法...

  protected void onCreate(Bundle savedInstanceState) {
    boolean needsOverlayPermission = false;
    if (getReactNativeHost().getUseDeveloperSupport() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      
      if (!Settings.canDrawOverlays(getContext())) {
        needsOverlayPermission = true;
        Intent serviceIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getContext().getPackageName()));
        FLog.w(ReactConstants.TAG, REDBOX_PERMISSION_MESSAGE);
        Toast.makeText(getContext(), REDBOX_PERMISSION_MESSAGE, Toast.LENGTH_LONG).show();
        ((Activity) getContext()).startActivityForResult(serviceIntent, REQUEST_OVERLAY_PERMISSION_CODE);
      }
    }
    
    //以上代码为 debug 模式下获得报错界面显示权限，如果失败，将以 toast 的方式提示
    

    if (mMainComponentName != null && !needsOverlayPermission) {
      loadApp(mMainComponentName);
    }
    mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
    // PS: 在 react native 的 debug 模式下，报错会以红屏出现，连按 RR 可以 reload app
  }

  protected void loadApp(String appKey) {
    if (mReactRootView != null) {
      throw new IllegalStateException("Cannot loadApp while app is already running.");
    }
    mReactRootView = createRootView();
    mReactRootView.startReactApplication(
      getReactNativeHost().getReactInstanceManager(),
      appKey,
      getLaunchOptions());
    getPlainActivity().setContentView(mReactRootView);
  }
//省略其他方法...

}
```

onCreate 方法主要生成 ReactRootView 这个关键的对象, 最终由 ReactRootView 启动整个应用。

由于 ReactRootView.java 代码比较长，就不放在这里了，具体思路如下：

首先创建一个 **UIThread**，来执行 关于UI 渲染的事务，其次创建一个 ReactInstanceManager 对象，用这个对象来创建 React 上下文。

**注意：应用启动到这里还是在 native 这边，还没有和 js 打交道**

下面来看 ReactInstanceManager 类的 createReactContext 方法的部分代码：

```jsx
 private ReactApplicationContext createReactContext(    
JavaScriptExecutor jsExecutor,
      JSBundleLoader jsBundleLoader) {
//.......
   //.......
    final CatalystInstance catalystInstance;
    try {
      catalystInstance = catalystInstanceBuilder.build();
    } finally {
      Systrace.endSection(TRACE_TAG_REACT_JAVA_BRIDGE);
      ReactMarker.logMarker(CREATE_CATALYST_INSTANCE_END);
    }

    if (mBridgeIdleDebugListener != null) {
      catalystInstance.addBridgeIdleDebugListener(mBridgeIdleDebugListener);
    }

    reactContext.initializeWithInstance(catalystInstance);
    catalystInstance.runJSBundle();
//  CatalystInstance 负责生成 Bridge 和 加载 js
    return reactContext;
  }

//
  private void attachMeasuredRootViewToInstance(
      ReactRootView rootView,
      CatalystInstance catalystInstance) {
    Systrace.beginSection(TRACE_TAG_REACT_JAVA_BRIDGE, "attachMeasuredRootViewToInstance");
    UiThreadUtil.assertOnUiThread();

    // Reset view content as it's going to be populated by the application content from JS
    rootView.removeAllViews();
    rootView.setId(View.NO_ID);
// 给每个 View  设置一个 ID
    
    
    UIManagerModule uiManagerModule = catalystInstance.getNativeModule(UIManagerModule.class);
    int rootTag = uiManagerModule.addMeasuredRootView(rootView);
    rootView.setRootViewTag(rootTag);
    @Nullable Bundle launchOptions = rootView.getLaunchOptions();
    WritableMap initialProps = Arguments.makeNativeMap(launchOptions);
    String jsAppModuleName = rootView.getJSModuleName();

    WritableNativeMap appParams = new WritableNativeMap();
    appParams.putDouble("rootTag", rootTag);
    appParams.putMap("initialProps", initialProps);
    // ！！由 catalysInstance 对象 开启 JS 世界的大门！！
    catalystInstance.getJSModule(AppRegistry.class).runApplication(jsAppModuleName, appParams);
    rootView.onAttachedToReactInstance();
    Systrace.endSection(TRACE_TAG_REACT_JAVA_BRIDGE);
  }

```

具体解释上面的代码：

在 ReactInstaceManager 创建上下文时，会委托 CatalystInstance 构建 Bridge 并 load和执行 js 脚本（index.android.bundle）。 这里用到了 Builder 设计模式来构建 CatalystInstance. 在 CatalystInstance 的帮助下，各个组件都注册到了 bridge 上。

从上面的启动过程可以看出： native 端相当于一个客户端的扮演者，他负责处理安卓原生事务(主要是渲染)，至于他要呈现什么内容，必须要问 js 端，js 端此时反而成为数据源，决定显示什么数据和什么组件。这也和最上面的架构图一致。一定程度上，react native 减轻了开发者的开发负担，让开发者能够直接面对 app 中最核心的问题。



事实上，React Native 是一个普通的安卓程序加上事件响应，在整个应用中，主要有2个线程：UIThread，JSThread。UIThread 循环向 JSThread请求数据，一旦数据有变，数据通过 bridge 转发到 UIThread，触发 UI 渲染，更新界面。

关于 BRIDGE：通信模型

![rn-arch2](/images/posts/android/rn-arch2.png)





### 2. DEBUG

明白上面 React Native 的运行原理，React Native 是怎么实现热更新的就很容易解释了，具体步骤如下：

1. 重新编译 jsx ，生成新的 js
2. 替换需要更新的 js
3. 解释执行 









