package chapter05;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public interface Lock {
    //永久阻塞,可以被中断
    void lock() throws InterruptedException;
    //可以被中断,增加了对应的超时功能
    void lock(long mills) throws InterruptedException, TimeoutException;
    //用来进行锁的释放
    void unlock();
    //获取当前有哪些线程被阻塞
    List<Thread> getBlockedThreads();
}
