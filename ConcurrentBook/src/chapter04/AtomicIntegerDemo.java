package chapter04;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    static AtomicInteger i=new AtomicInteger();
    public static class AddThread implements Runnable{
        @Override
        public void run() {
            for (int i1 = 0; i1 < 10000; i1++) {
                i.incrementAndGet();//使用cas操作将自己加1, 同时也会返回当前值.
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts=new Thread[10];
        for (int i1 = 0; i1 < 10; i1++) {
            ts[i1]=new Thread(new AddThread());
        }
        for (int k = 0; k < 10; k++) {
            ts[k].start();
        }
        for (int k = 0; k < 10; k++) {
            ts[k].join();
        }
        System.out.println(i.get());
    }
}
