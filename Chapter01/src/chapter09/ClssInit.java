package chapter09;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ClssInit {
    static {
        try {
            System.out.println(Thread.currentThread().getName());
            System.out.println("the class will be invoke");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IntStream.range(0,5).forEach(i->{
            System.out.println(i);
            new Thread(ClssInit::new);
        }
        );
    }
}
