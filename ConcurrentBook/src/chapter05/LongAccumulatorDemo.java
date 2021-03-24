package chapter05;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAccumulator;

public class LongAccumulatorDemo {
    public static void main(String[] args) throws InterruptedException {
        LongAccumulator ac=new LongAccumulator(Long::max,Long.MIN_VALUE);
        Thread[] threads=new Thread[10000];
        for (int i = 0; i < 1000; i++) {
            threads[i]=new Thread(()->{
                Random random=new Random();
                long value=random.nextLong();
                ac.accumulate(value);
//                ConcurrentHashMap
            });
            threads[i].start();
        }
        for (int i = 0; i < 1000; i++) {
            threads[i].join();
        }
        System.out.println(ac.longValue());
    }
}
