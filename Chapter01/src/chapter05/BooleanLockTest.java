package chapter05;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

public class BooleanLockTest {
    private final Lock lock =new BooleanLock();
    public void syncMethod() {
        try {
            lock.lock();
            int randomInt=current().nextInt(10);
            System.out.println(currentThread() + "get the lock");
            TimeUnit.SECONDS.sleep(randomInt);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BooleanLockTest blt=new BooleanLockTest();
        IntStream.range(0,10).mapToObj(i->new Thread(blt::syncMethod)).forEach(Thread::start);
    }
}
