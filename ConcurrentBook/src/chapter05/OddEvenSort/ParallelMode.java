package chapter05.OddEvenSort;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelMode {
    static ExecutorService pool=Executors.newCachedThreadPool();
    static int exchangeFlag=1;

    public static int getExchangeFlag() {
        return exchangeFlag;
    }

    static synchronized void setExchangeFlag(int v){
        exchangeFlag=v;
    }

    public static class OddEvenSortTask implements Runnable{
        int[]  arr;
        int i;
        CountDownLatch latch;

        public OddEvenSortTask(int i, CountDownLatch latch,int[] arr) {
            this.i = i;
            this.latch = latch;
            this.arr=arr;
        }

        @Override
        public void run() {
            if (arr[i]>arr[i+1]){
                int temp=arr[i];
                arr[i]=arr[i+1];
                arr[i+1]=temp;
                setExchangeFlag(1);
            }
            latch.countDown();
        }
    }

    public static void pOddEvenSortTask(int[] array) throws InterruptedException {
        int start=0;
        while (getExchangeFlag()==1||start==1){
            setExchangeFlag(0);
            //计算latch应该有几个.
            CountDownLatch latch=new CountDownLatch(array.length/2-(array.length%2==0?start:0));
            for (int i=start;i<array.length-1;i+=2){
                pool.submit(new OddEvenSortTask(i,latch,array));
            }
            latch.await();
            if (start==0) start=1;
            else start=0;
        }
    }

}
