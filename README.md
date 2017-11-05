# 环境搭建

## Git 环境搭建

* gogs.io
* ec和example两个测试依赖是不能被放在 core 中的

    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
            exclude group: 'com.android.support', module: 'support-annotations'
        })
        testCompile 'junit:junit:4.12'


## 项目初始化

* 全局采用唯一的 Activity，界面全部采用 Fragment

* 写方法或者类，尽量让类成员（方法、属性）和方法中的变量的不可变性达到最大化
    Jvm 会对 final 进行优化

* 类多代码少，架构设计原则

* 代码越简洁，命名的单词越短，越简洁，不需要 set xxx


## 网络框架搭建


## Loading 搭建

* 父类 Activity 尽量使用 AppCompatActivity, 保证兼容到更多的手机设备

* LinkedHashMap 和 HashMap 区别: LinkedHashMap 是有序的

* 要做一个新功能钱，先去 github 上看看，有没有现成的，别当造轮子大师(仅仅针对项目开发)

## 

* Android 中，资源名称不能使用 “-”, 应该使用 "_"

## 需要更深入研究

* icon 字体库
* Fragmentation
* RxJava , RxJava 结合 Retrofit

