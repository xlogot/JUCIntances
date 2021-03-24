package chapter05.JDKFuture;

import java.lang.management.ManagementFactory;
import java.util.concurrent.*;

public class FutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> future=new FutureTask<>(new RealData("a"));

        new Thread(future).start();

//        ExecutorService executor = Executors.newFixedThreadPool(1);
//
//        executor.submit(future);
        System.out.println("请求完毕");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("data= "+ future.get());
//        executor.shutdown();
    }
}
