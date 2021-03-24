package chapter05;

import java.util.*;
import java.util.concurrent.TimeoutException;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.lang.Thread.currentThread;

public class BooleanLock implements Lock {
    //BooleanLock锁,通过控制一个Boolean变量的开关来控制是否允许当前的线程获取锁

    //拥有当前锁的线程
    private Thread currentThread;
    //是否有线程获取了该锁
    private boolean locked=false;
    //线程在获取当前线程时进入了阻塞状态
    private final  List<Thread> bolckedList =new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this){
            while (locked){
//                bolckedList.add(currentThread());
//                this.wait();
                final Thread tempThread=currentThread();
                try {
                    if (!bolckedList.contains(tempThread)) bolckedList.add(tempThread);
                    this.wait();
                } catch (InterruptedException e) {
                    bolckedList.remove(tempThread);
                    throw e;
                }
            }
            bolckedList.remove(currentThread());
            this.locked=true;
            this.currentThread=currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this){
            if (mills<=0){
                this.lock();
            }else{
                long remainingMills =mills;
                long endMills=currentTimeMillis()+remainingMills;
                while (locked){
                    if (remainingMills<=0) throw  new TimeoutException("cannot get the lock during " +mills);
//                    if(!bolckedList.contains(currentThread)) bolckedList.add(currentThread());
//                    this.wait(remainingMills);
                    final Thread tempThread=currentThread();
                    try {
                        if (!bolckedList.contains(tempThread)) bolckedList.add(tempThread);
                        this.wait();
                    } catch (InterruptedException e) {
                        bolckedList.remove(tempThread);
                        throw e;
                    }
                    remainingMills=endMills-currentTimeMillis();
                }
                bolckedList.remove(currentThread());
                this.locked=true;
                this.currentThread=currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this){
            if (currentThread==currentThread()){
                this.locked=false;
                Optional.of(currentThread().getName() + " release the the lock.").ifPresent(out::println);
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(bolckedList);
    }
}
