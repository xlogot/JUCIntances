package chapter05.StreamCountDemo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Multiply implements Runnable {
    public static BlockingQueue<Msg> bq=new LinkedBlockingDeque<>();
    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){
            try {
//                if (bq.isEmpty()) break;
                Msg msg=bq.take();
                msg.j=msg.i*msg.j;
                Div.bq.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
