package chapter03;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseFruitExample {
    private static List<String> sons= Arrays.asList("1","2");

    public static void main(String[] args) {
        List<String> fruits=Arrays.asList("apple","orange");
        List<PurcaseFuritTask> tasks= fruits.stream().map(f->buy(f)).collect(Collectors.toList());
        tasks.forEach(Thread::start);
        tasks.forEach(f->{
            try {
                f.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        List<String> result=tasks.stream().map(PurcaseFuritTask::get).collect(Collectors.toList());
    }

    private static PurcaseFuritTask buy(String fruits1) {
        return new PurcaseFuritTask(fruits1);
    }


}
