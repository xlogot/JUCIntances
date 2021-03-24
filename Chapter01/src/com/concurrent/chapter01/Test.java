package com.concurrent.chapter01;

public class Test implements Runnable {
    @Override
    public void run() {
        System.out.println(111111);
    }

    public static void main(String[] args) {
        Test test=new Test();
        Thread t1=new Thread(test);
        t1.start();
    }
}
