#铁路动环监控系统
-----
列表页交互：
分页： 服务端分页
下拉：刷新整个页面
上拉：加载下一页面

RabbitMQ

Http

Getui

9.27工作内容：
修改需求文档

9.27开发内容： 改页面：
1 登录页
2 首页
3 四个fragment
4 

9.28 开发内容：
集成个推
个推处理

9.29 开发内容：
集成百度地图，或者放一张图片

9.30 开发内容：
百度地图

~~~
colorPrimary //导航栏颜色
colorPrimaryDark //状态栏颜色
colorAccent //控件主题颜色， 控制各个控件被选中时的颜色
windowBackground //默认界面背景颜色
android:navigationBarColor // 底部导航栏颜色
textColorPrimary //默认文字主题颜色
~~~   

---------------------

适配全面屏， 刘海屏：
https://blog.csdn.net/xiangzhihong8/article/details/80317682

android4.4版本的手机上部署后：toolbar和statusbar重叠：
添加：android:fitsSystemWindows="true"

地图上展示图钉分两种：
1 用Overlay绘制所有点
2 用点聚合绘制， // https://blog.csdn.net/u010635353/article/details/52386097
  自定义点聚合图标样式：https://blog.csdn.net/ulddfhv/article/details/81233755

Activity/Fragment 输入法弹出底部菜单上移：
manifest.xml：对应的Activity添加：android:windowSoftInputMode="adjustPan|stateVisible"

-----------2018.10.8-----------
整理接口：
 - http接口，
 - 个推接口，
 - RabbitMQ接口

个推：集成个推

百度地图： 集成点聚合，在地图上的点，添加infoWindow

通信库集成： httpUtil,  RabbitMQ

抽style.

------------
第一次调用retrofit.getApi()，请求失败，解决的办法是：converterFactory不匹配，创建了一个StringConverterFactory,就OK了。

-----------------
使用RecyclerView
1 xml里布局：EasyRecyclerView, 包括emptyView布局xml。
2 创建新的ViewHolder，继承 RecyclerView.ViewHolder。
3 创建RecyclerAdapter，继承RecyclerView.Adapter<RecyclerView.ViewHolder>。
4 
5 


