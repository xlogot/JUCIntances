package chapter05.StreamCountDemo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Div implements Runnable {
    public static BlockingQueue<Msg> bq=new LinkedBlockingDeque<>();
    @Override
    public void run() {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){
            if (bq.isEmpty()) break;
            try {
                Msg msg=bq.take();
                msg.i=msg.i/2;
//                System.out.println(msg.orgStr+"= "+ msg.i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis());
    }
}
