package chapter03;

import java.util.concurrent.TimeUnit;

public class FlagThreadExit {
    static class MyTask extends Thread{
        private volatile boolean closed=false;

        @Override
        public void run() {
        while (!closed&&!isInterrupted()){
            //do something here;
        }
        }
        public void close(){
            this.closed=true;
            this.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask t=new MyTask();
        t.start();
        TimeUnit.MINUTES.sleep(1);
        t.close();
    }
}
