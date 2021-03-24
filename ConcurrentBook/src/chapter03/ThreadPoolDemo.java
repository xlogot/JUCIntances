package chapter03;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static class MyTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            return -1;
        }
    }

    public static void main(String[] args) {
//        MyTask task=new MyTask();
//        ExecutorService service = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 10; i++) {
//            Future<Integer> submit = service.submit(task);
//            try {
//                Integer integer = submit.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//        service.shutdown();
    }
}
