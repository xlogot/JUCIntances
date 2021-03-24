package chapter03;

import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class ThreadIsInterrupted {
//    public static void main(String[] args) throws InterruptedException {
//        Thread t1=new Thread(){
//            @Override
//            public void run() {
//                while (true){
//                    //
//                }
//            }
//        };
//        t1.start();
//        TimeUnit.MILLISECONDS.sleep(2);
//        System.out.println("thread is interrupted "+t1.isInterrupted());
//        t1.interrupt();
//        System.out.println("thread is interrupted "+t1.isInterrupted());
//    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.println(isInterrupted()+" is interrupted");
                    }
                }
            }
        };
        t1.setDaemon(true);
        t1.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println("thread is interrupted "+t1.isInterrupted());
        t1.interrupt();//t1 isInterrupted is true;
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println("thread is interrupted "+t1.isInterrupted());
    }
}
