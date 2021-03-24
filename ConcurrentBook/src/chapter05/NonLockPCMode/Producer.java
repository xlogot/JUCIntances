package chapter05.NonLockPCMode;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

//生产者
//生产者需要一个ringBuffer的引用, 也就是环形缓冲区, 他有一个重要的方法pushData(),
// 产生的数据推入缓冲区.方法pushData()接受一个ByteBuffer 对象, 在ByteBuffer对象中
// 以用来包装任何数据类型。这里用来存储long整数, pushData()法的功能就是将
// ByteBuffer对象中的数据提取出来，并装载到环形缓冲区中。
public class Producer {
    private  final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer bb){
        long sequence=ringBuffer.next();
        try {
            PCData event=ringBuffer.get(sequence);
            event.setValue(bb.getLong(0));
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
