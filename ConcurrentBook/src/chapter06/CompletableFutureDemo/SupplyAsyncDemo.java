package chapter06.CompletableFutureDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SupplyAsyncDemo {
    public static Integer calc(Integer data){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return data+data;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final CompletableFuture<Integer> future=CompletableFuture.supplyAsync(()->calc(30));
        future.get();
        CompletableFuture<Void> future1 = CompletableFuture
                .supplyAsync(() -> calc(30))
                .thenApply(integer -> integer.toString())
                .thenApply(s -> "/" + s + "/")
                .thenAccept(System.out::println);
        future1.get();


    }
}
