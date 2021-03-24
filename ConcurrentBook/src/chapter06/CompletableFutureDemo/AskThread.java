package chapter06.CompletableFutureDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AskThread implements Runnable {
    CompletableFuture<Integer> re=null;

    public AskThread(CompletableFuture<Integer> re) {
        this.re = re;
    }

    @Override
    public void run() {
        int i=0;
        try {
            i=re.get();//和futureTask一样, 调用get()方法是会被阻塞.
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }

    public static void main(String[] args) {
        final CompletableFuture<Integer> future=new CompletableFuture<>() ;
        new Thread(new AskThread(future)).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        future.complete(10);
    }
}
