package chapter04;


import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalRandomDemo {
    public static final int GEN_COUNT=10000000;
    public static final int THREAD_COUNT=4;
    static ExecutorService es= Executors.newFixedThreadPool(THREAD_COUNT);
    public static Random rnd=new Random(123);

    public static ThreadLocal<Random> tRnd=new ThreadLocal<Random>(){
        @Override
        protected Random initialValue() {
            return new Random(123);
        }
    };
    public static class RndTask implements Callable<Long>{
        private int mode=0;

        public RndTask(int mode) {
            this.mode = mode;
        }

        public Random getRandom(){
            if (mode==0) return rnd;
            else if (mode==1) return tRnd.get();
            else return null;
        }

        @Override
        public Long call() throws Exception {
            long b=System.currentTimeMillis();
            for (int i = 0; i < GEN_COUNT; i++) {
                getRandom().nextInt();
            }
            long e=System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+ "spend " +(e-b)+ "ms");
            return e-b;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Long>[] futureTasks=new  FutureTask[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            futureTasks[i] = (FutureTask<Long>) es.submit(new RndTask(0));
        }
        int totalTime=0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            totalTime+=futureTasks[i].get();
        }
        System.out.println("===================");
        for (int i = 0; i < THREAD_COUNT; i++) {
            futureTasks[i] = (FutureTask<Long>) es.submit(new RndTask(1));
        }
        totalTime=0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            totalTime+=futureTasks[i].get();
        }
        es.shutdown();
//        AtomicInteger

    }
}
