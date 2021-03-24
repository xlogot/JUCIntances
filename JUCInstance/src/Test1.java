import sun.reflect.generics.tree.VoidDescriptor;
import sun.security.acl.WorldGroupImpl;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test1 {
    ReentrantLock lock=new ReentrantLock();
    Condition condition=lock.newCondition();

    AtomicBoolean aBoolean=new AtomicBoolean(false);

    volatile boolean flag=false;

    CyclicBarrier barrier=new CyclicBarrier(1, new Runnable() {
        int number=0;
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" "+number++);
        }
    });

    int number=0;
    void printNumber() throws InterruptedException {
        lock.lock();
        while (number<=26){
            System.out.println(Thread.currentThread().getName()+" "+(number++));
            condition.signal();
            condition.await();
        }
        condition.signal();
        lock.unlock();
    }
    void printNumber1(){
        //是实际交换的, 但是不能同一在控制台看到交替执行.
        while (number<=26){
            while (!aBoolean.compareAndSet(false,true)){

            }
            System.out.println(Thread.currentThread().getName()+" "+number++);
        }
    }
    void printNumber2(){
        //是实际交换的, 但是不能同一在控制台看到交替执行.
        while (number<=26){
            while (flag){

            }
            System.out.println(Thread.currentThread().getName()+" "+number++);
            flag=true;
        }
    }
    void printNumber3() throws BrokenBarrierException, InterruptedException {
        //是实际交换的, 但是不能同一在控制台看到交替执行.
        while (number<=26){
            barrier.await();
            System.out.println(Thread.currentThread().getName()+" "+number++);
            flag=true;
        }
    }

    char end='a';
    void printWord() throws InterruptedException {
        lock.lock();
        while (end<='z'){
            System.out.println(Thread.currentThread().getName() +end++);
            condition.signal();
            condition.await();
        }
        condition.signal();
        lock.unlock();
    }
    void printWord1(){
        while (end <= 'z') {
            while (!aBoolean.compareAndSet(true, false)) {

            }
            System.out.println(Thread.currentThread().getName() + " " + end++);
        }
    }
    void printWord2(){
        while (end <= 'z') {
            while (!flag) {

            }
            System.out.println(Thread.currentThread().getName() + " " + end++);
            flag=false;
        }
    }
    void printWord3() throws BrokenBarrierException, InterruptedException {
        while (end<='z'){
            barrier.await();
            System.out.println(Thread.currentThread().getName() + " " + end++);
        }
    }

    public static void main (String[] args) {
        Test1 t=new Test1();
//        new Thread(()->{
//            try {
//                t.printNumber();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"111111").start();
//        new Thread(()->{
//            try {
//                t.printWord();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"2").start();


        new Thread(()->{
            try {
                t.printWord3();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"2").start();

    }
}
