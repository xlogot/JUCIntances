package chapter03;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("t1 has been interrupted");
            }
        });
        t1.start();

        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
    }
}
