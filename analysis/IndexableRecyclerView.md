### IndexableRecyclerView module的使用
开源地址：https://github.com/YoKeyword/IndexableRecyclerView
RecyclerView的深入理解。算是进阶吧！
需要完成的功能是联系人的滑动，点击旁边的条目有反应。
删除整个题目有反应。就是数据的更新那一块。
加入最基本的RecyclerView的使用。
#####1.移植项目
>01.Android Studio中有六种依赖

首先分析compile和provided
```
1. compile 'com.android.support:appcompat-v7:22.2.1'
2. provided 'com.squareup.dagger:dagger-compiler:1.2.1'
3. compile files('libs/picasso-2.4.0.jar')
4. compile project(':androidPullToRefresh')
```

1.是从repository（默认是jCenter())里下载一个依赖包进行编译并打包
3.是从本地的libs目录下寻找picasso-2.4.0.jar这个文件进行编译并打包。类似的命令有`compile fileTree(dir: 'libs', include: '*.jar')`——将libs目录下所有jar文件进行编译并打包。
4.是将另一个module（等同eclipse中的library)进行编译并打包
2.provided，是提供给那些只编译不打包场景的命令。就是，我在编译的时候对某一个jar文件有依赖，但是最终打包apk文件时，我不想把这个jar文件放进去，可以用这个命令。

下面是具体的分析：：<br>
1.Compile<br>
compile是对所有的build type以及favlors都会参与编译并且打包到最终的apk文件中。<br>
2.Provided<br>
Provided是对所有的build type以及favlors只在编译时使用，类似eclipse中的external-libs,只参与编译，不打包到最终apk。<br>
3.APK<br>
只会打包到apk文件中，而不参与编译，所以不能再代码中直接调用jar中的类或方法，否则在编译时会报错<br>
4.Test compile<br>
Test compile 仅仅是针对单元测试代码的编译编译以及最终打包测试apk时有效，而对正常的debug或者release apk包不起作用。<br>
5.Debug compile<br>
Debug compile 仅仅针对debug模式的编译和最终的debug apk打包。<br>
6.Release compile<br>
Release compile 仅仅针对Release 模式的编译和最终的Release apk打包。