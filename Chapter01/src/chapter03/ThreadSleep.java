package chapter03;

import java.util.concurrent.TimeUnit;

public class ThreadSleep {
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            long startTime=System.currentTimeMillis();
            sleep(2_00L);
            long endTime=System.currentTimeMillis();
            System.out.println(String.format("Total spend %d ms",(endTime-startTime)));
        }).start();

        long startTime=System.currentTimeMillis();
        sleep(3_00L);
        TimeUnit.SECONDS.sleep(10);
        long endTime=System.currentTimeMillis();
        System.out.println(String.format("Total spend %d ms",(endTime-startTime)));
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
