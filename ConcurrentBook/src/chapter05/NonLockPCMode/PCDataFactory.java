package chapter05.NonLockPCMode;

import com.lmax.disruptor.EventFactory;

//PCData对象的工厂类, 会在disruptor框架初始化时, 构造所有的缓冲区中的对象实例
public class PCDataFactory implements EventFactory<PCData> {
    @Override
    public PCData newInstance() {
        return new PCData();
    }
}
