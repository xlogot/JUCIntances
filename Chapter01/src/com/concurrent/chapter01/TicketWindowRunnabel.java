package com.concurrent.chapter01;

public class TicketWindowRunnabel implements Runnable {

    private int index=1;
    private static final int MAX=100;

    @Override
    public void run() {
        while (index<=MAX){
            System.out.println(Thread.currentThread()+" 的号码是："+(index++) );
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final TicketWindowRunnabel task=new TicketWindowRunnabel();
        Thread t1=new Thread(task,"1号窗口");
        Thread t2=new Thread(task,"2号窗口");
        Thread t3=new Thread(task,"3号窗口");
        Thread t4=new Thread(task,"4号窗口");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
