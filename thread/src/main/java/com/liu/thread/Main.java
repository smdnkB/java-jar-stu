package com.liu.thread;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        System.out.println(myThread.isDaemon());
        myThread.setDaemon(true); // 设置为守护线程 主线程结束后立即停止。默认为非守护线程：主线程等待myThread结束
        myThread.start();
        myThread.join(); // 主线程卡在这里等待 myThread 结束

        myThread.getName();// 线程名称
        myThread.getId(); // 线程 id
        myThread.getPriority();// 优先级
        myThread.interrupt(); // 中断 (线程处于sleep  wait 阻塞状态时 可通过中断唤醒)
        Thread.interrupted();// 判断线程是否被中断 判断后清除中断标记
        myThread.isInterrupted(); // 判断线程是否被中断 判断后不会清除中断标记
        Thread.currentThread();// 返回实际执行这句代码的线程对象
        myThread.setUncaughtExceptionHandler(null); // 线程出现异常时，此方法用于建立异常控制器

    }
}
