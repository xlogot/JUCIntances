package chapter04;


import java.util.concurrent.TimeUnit;

public class TicketRunnable implements Runnable {

    private int index=1;
    private static final int MAX=100;

    private Object MUTEX=new Object();

    @Override
    public void run() {
            while (index <= MAX) {
        synchronized (MUTEX) {
                System.out.println(Thread.currentThread() + " 的号码是：" + (index++));
            }
        }
//        try {
//        TimeUnit.MILLISECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {
//        final TicketRunnable task=new TicketRunnable();
//        Thread t1=new Thread(task,"1号窗口");
//        Thread t2=new Thread(task,"2号窗口");
//        Thread t3=new Thread(task,"3号窗口");
//        Thread t4=new Thread(task,"4号窗口");
//
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
//        for (int i = 0; i < 4; i++) {
//            new Thread(TicketRunnable::new).start();
//        }
        TicketRunnable t1=new TicketRunnable();
        TicketRunnable t2=new TicketRunnable();
        Thread th1=new Thread(t1,"th1");
        Thread th2=new Thread(t2,"th2");
        Thread th3=new Thread(t1,"th3");
        Thread th4=new Thread(t2,"th4");

        th1.start();
        th2.start();
        th3.start();
        th4.start();

    }
}
