package com.liu.runable;

public class MyRunnable implements Runnable{
    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }

        System.out.println("Runnable complete!");
    }
}
