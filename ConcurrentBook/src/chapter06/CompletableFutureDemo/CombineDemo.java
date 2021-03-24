package chapter06.CompletableFutureDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CombineDemo {
    public static Integer calc(Integer data){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return data/0;
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> intFuture = CompletableFuture . supplyAsync(() -> calc(50));
        CompletableFuture<Integer> intFuture2 = CompletableFuture. supplyAsync(()-> calc(25));
        CompletableFuture<Void> fu = intFuture
                .thenCombine (intFuture2, (i, j) -> (i +j))
                .thenApply((str) -> "/"+ str + "/")
                .thenAccept (System. out::println) ;
                fu.get();
    }
}
