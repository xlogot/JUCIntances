package chapter04;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class ThisMonitor {
    public synchronized void method1(){
        System.out.println(currentThread().getName()+" enter to method1");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public /*synchronized*/ void method2() {//不同的线程争抢的是这个类的锁
        synchronized (this) {//和在方法的签名上使用synchronized关键字是一样的效果
            System.out.println(currentThread().getName() + " enter to method2");
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThisMonitor t=new ThisMonitor();
        new Thread(t::method1,"T1").start();
        new Thread(t::method2,"T2").start();
    }
}
