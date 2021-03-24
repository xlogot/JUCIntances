package chapter05;

import java.awt.*;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class FalseSharing implements Runnable {
    //CPU真正的核心数
    public final static int NUM_Threads=6;
    public final  static long ITERATIONS =500l*100L*1000L;
    private  final int arrayIndex;
    private static VolatileLong[] longs= new VolatileLong[NUM_Threads];
    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i]=new VolatileLong();
        }
    }

    public FalseSharing(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(String[] args) throws InterruptedException {

//        LongAccumulator
        final  long start=System.currentTimeMillis();
        runTest();
        System.out.println("duration : " + (System.currentTimeMillis()-start) );
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads=new Thread[NUM_Threads];
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(new FalseSharing(i));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    @Override
    public void run() {
        long i=ITERATIONS+1;
        while (0 != --i) {
            longs[arrayIndex].value=i;
        }
    }

    private static class VolatileLong {
        public volatile long value=0l;
        //有没有这行,性能差距很大.
        public long p1,p2,p3,p4,p5,p6,p7;
    }
}
