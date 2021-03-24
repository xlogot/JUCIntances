package chapter03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemapDemo implements Runnable {
    final Semaphore semap=new Semaphore(4);
    @Override
    public void run() {
        try {
            semap.acquire();
            while (true){
                int i=0;
                i++;
                if (i<Integer.MAX_VALUE) i=0;
            }
//            Thread.sleep(900);
//            System.out.println(Thread.currentThread().getName()+" done!");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            semap.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(4);
        final SemapDemo demo=new SemapDemo();
        for (int i = 0; i < 4; i++) {
            service.execute(demo);
        }
        while (true){
            int i=0;
            i++;
            if (i<Integer.MAX_VALUE) i=0;
        }
    }
}
