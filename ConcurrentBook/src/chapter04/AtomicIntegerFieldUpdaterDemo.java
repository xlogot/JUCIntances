package chapter04;

import java.util.Calendar;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterDemo {
    public static class Candidate{
        int id;
        volatile int score;
    }
    public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdate=AtomicIntegerFieldUpdater.newUpdater(Candidate.class,"score");
    public static AtomicInteger allScore=new AtomicInteger(0);

    public static void main(String[] args) {
        final Candidate stu=new Candidate();
        //不能乱用Executors类, 创建线程池
        ExecutorService service = new ThreadPoolExecutor(
                10,
                15,
                100l,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                new  ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i <100000; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    if (Math.random() > 0.4) {
                        scoreUpdate.incrementAndGet(stu);
                        allScore.incrementAndGet();
                    }
                }
            });
        }
        service.shutdown();
        System.out.println("score= "+stu.score);
        System.out.println("allScore " + allScore);
    }
}
