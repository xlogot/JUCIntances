package chapter03;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockCondition implements Runnable {
    public static ReentrantLock lock=new ReentrantLock();
    public static Condition condition=lock.newCondition();
    @Override
    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("thread is going on.");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition t1=new ReenterLockCondition();
        Thread t2=new Thread(t1);
        t2.start();
        Thread.sleep(2000);
        lock.lock();
        //调用此方法时, 要求相关线程获得锁.
        condition.signal();
        lock.unlock();

    }
}
