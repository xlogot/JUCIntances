package chapter04;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalDemo_GC {
    static volatile ThreadLocal<SimpleDateFormat> t1=new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected void finalize() throws Throwable {
//            super.finalize();
            System.out.println(this.toString()+" out is gc");
        }
    };
    static volatile CountDownLatch cd=new CountDownLatch(10000);
    public static class ParseDate implements Runnable{
        int i=0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if (t1.get()==null){
                    t1.set((new SimpleDateFormat(){
                        @Override
                        protected void finalize() throws Throwable {
//                            super.finalize();
                            System.out.println(this.toString()+" inner is gc");
                        }
                    }));
                    System.out.println(Thread.currentThread().getId()+": create simpledateFormat");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                cd.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es= Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10000; i++) {
            es.execute(new ParseDate(i));
        }
        cd.await();
        System.out.println("complete");
        t1=null;
        System.gc();
        System.out.println("1 gc complete");
        t1=new ThreadLocal<>();
        cd=new CountDownLatch(10000);
        for (int i = 0; i < 10000; i++) {
            es.execute(new ParseDate(i));
        }
        cd.await();
        TimeUnit.SECONDS.sleep(1);
//        t1=null;
        es.shutdown();
        System.gc();
        System.out.println("2 gc complete");

    }
}
