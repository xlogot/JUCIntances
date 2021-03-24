package chapter03;

import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        System.out.println(Thread.interrupted());

        Thread.currentThread().interrupt();
        /*
        如果一个线程设置了interrupt标识，那么接下来可中断方法会立即中断。
         */

        System.out.println(Thread.currentThread().isInterrupted());

        try {
            System.out.println("sleep------------");
            //会被立即中断
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("i will be interrupted still ");
        }
    }
}
