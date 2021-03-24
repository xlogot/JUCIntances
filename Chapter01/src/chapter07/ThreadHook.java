package chapter07;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.concurrent.TimeUnit;

public class ThreadHook {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("the hook thread 1 is running");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("the hook thead 1 will exit");
            }
        });
        //钩子线程可以注册多个
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("hook thread 2 is running");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("hook thread 2 will exit");
            }
        });

        //注入了两个钩子线程.
        System.out.println("the program is stopping");
    }
}
