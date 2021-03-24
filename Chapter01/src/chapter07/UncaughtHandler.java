package chapter07;

import java.util.concurrent.TimeUnit;

public class UncaughtHandler {

    public static void main(String[] args) {

        final Thread thread = new Thread(() ->
        {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            //②这里会出现unchecked异常
            //here will throw unchecked exception .
            System.out.println(1 / 0);
        }, "Test-Thread");
        thread.setUncaughtExceptionHandler((t,e)->{
            System.out.println(Thread.currentThread().getName());
            System.out.println(t.getName() + " occur exception");
        });
        thread.start();
    }
}
