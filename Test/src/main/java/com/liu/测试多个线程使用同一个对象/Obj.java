package com.liu.测试多个线程使用同一个对象;

public class Obj {

    private int i = 10;

    public int getI(){
        i++;
        return i;
    }
}
