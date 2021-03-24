package chapter06.StampedLockDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

public class StampedLockCPUDemo {
    static Thread[] holdCpuThreads=new Thread[3];
    static final StampedLock LOCK=new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        new Thread(){
            @Override
            public void run() {
                long readLong= LOCK.writeLock();
                LockSupport.parkNanos(60_000_000_000L);
                System.out.println("unlock");
                LOCK.unlockWrite(readLong);
            }
        }.start();
        Thread.sleep(100);
        for (int i = 0; i < 3; i++) {
            holdCpuThreads[i]=new Thread(new HoldCPUReadThread());
            holdCpuThreads[i].start();
        }
        Thread.sleep(1000);
        System.out.println("interrupt");
        for (int i = 0; i < 3; i++) {
            holdCpuThreads[i].interrupt();
        }

    }
    public static class HoldCPUReadThread implements Runnable{
        @Override
        public void run() {
            long lockr=LOCK.readLock();
            System.out.println(Thread.currentThread().getName()+" 获得读锁");
            LOCK.unlockRead(lockr);
        }
    }
}
