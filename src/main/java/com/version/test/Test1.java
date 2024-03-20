package com.version.test;

import com.version.control.annotation.Version;

@Version("1.0")
public class Test1 implements Test {
    public Test1() {
        System.out.println("这里是Test1的构造方法");
    }

    @Override
    public void print() {
        System.out.println("这里是Test1的继承");
    }
}
