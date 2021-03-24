package com.concurrent.chapter01;

public class TicketWindow extends Thread {
    private final String name;
    private static final int MAX=100;
    private static int index=1;
    public TicketWindow(String name){
        this.name=name;
    }




    @Override
    public void run() {
        while (index<=MAX){
            //因为下面这条语句不是原子性的，不同的线程访问index时，可能会得到一样的数。
            System.out.println("柜台"+name+" 当前号码是 "+ index++);
//            index++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TicketWindow t1=new TicketWindow("1号柜");
        t1.start();
        TicketWindow t2=new TicketWindow("2号柜");
        t2.start();
        TicketWindow t3=new TicketWindow("3号柜");
        t3.start();
        TicketWindow t4=new TicketWindow("4号柜");
        t4.start();
    }
}
