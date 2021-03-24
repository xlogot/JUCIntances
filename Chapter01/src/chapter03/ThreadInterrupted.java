package chapter03;

import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class ThreadInterrupted {
    public static void main(String[] args) throws InterruptedException {
    Thread t1=new Thread(){
        @Override
        public void run() {
            while (true){
                System.out.println(Thread.interrupted());
            }
        }
    };
    t1.setDaemon(true);
    t1.start();
    //
        TimeUnit.MILLISECONDS.sleep(1);
        t1.interrupt();
    }

}
