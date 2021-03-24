package chapter05.NonLockPCMode;

import com.lmax.disruptor.WorkHandler;

public class Consumer implements WorkHandler<PCData> {
    @Override
    public void onEvent(PCData pcData) throws Exception {
        System.out.println(Thread.currentThread().getName()+" : event : --"+ pcData.getValue()*pcData.getValue()+"--");
    }
}
