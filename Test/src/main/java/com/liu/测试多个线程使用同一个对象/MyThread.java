package com.liu.测试多个线程使用同一个对象;

public class MyThread extends Thread{
    private Obj obj ;
    public MyThread(Obj obj){
        this.obj = obj;
    }

    @Override
    public void run() {

    }
}
