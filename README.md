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



## 需要更深入研究

* icon 字体库
* Fragmentation
* RxJava , RxJava 结合 Retrofit

