package com.version.test;

import com.version.control.annotation.Version;

@Version("2.0")
public class Test2 implements Test {

    public Test2() {
        System.out.println("这里是Test2的构造方法");
    }

    @Override
    public void print() {
        System.out.println("这里是Test2的继承");
    }
}
