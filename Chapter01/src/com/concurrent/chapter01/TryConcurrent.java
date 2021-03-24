package com.concurrent.chapter01;

import java.util.concurrent.TimeUnit;

public class TryConcurrent {
    public static void main(String[] args) throws InterruptedException {
        //第一种开启新的线程的方法，重写thread类中的run方法。
//        new Thread(){
//            @Override
//            public void run() {
//                enjoyMusic();
//            }
//        }.start();
        //lambda

        //第二种开启线程的算法
//        new Thread(TryConcurrent::enjoyMusic).start();
//        browserNews();
        Thread thread=new Thread(){
            @Override
            public void run() {
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            }
        };
        thread.start();

        TimeUnit.SECONDS.sleep(10);

        thread.start();


    }

    static void browserNews(){
        while (true){
            System.out.println("new");
        }
    }
    static void enjoyMusic(){
        while (true){
            System.out.println("music");
        }
    }
    static void sleep(int second){
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
