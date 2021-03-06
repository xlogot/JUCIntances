package chapter05.NonLockPCMode;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.ThreadFactory;

//
public class Client {
    public static void main(String[] args) throws InterruptedException {
        PCDataFactory factory=new PCDataFactory();
        int bufferSize=1024;
        Disruptor<PCData> disruptor=new Disruptor<PCData>(factory, bufferSize, r -> {
            return new Thread(r);
        });
        disruptor.handleEventsWithWorkerPool(
                new Consumer(),
                new Consumer(),
                new Consumer());
        disruptor.start();

        RingBuffer<PCData> ringBuffer=disruptor.getRingBuffer();
        Producer producer=new Producer(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l=0;true;l++){
            bb.putLong(0,l);
            producer.pushData(bb);
            Thread.sleep(100);
            System.out.println("add data : " +l);
        }
    }
}
