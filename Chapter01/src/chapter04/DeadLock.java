package chapter04;

public class DeadLock {
    private final Object MUTEX_READ=new Object();
    private final Object MUTEX_WRITE=new Object();
    public void read(){
        synchronized (MUTEX_READ){
            System.out.println(Thread.currentThread().getName()+" get read lock");
            synchronized (MUTEX_WRITE){
                System.out.println(Thread.currentThread().getName()+" get write lock");
            }
            System.out.println(Thread.currentThread().getName()+" release write lock");
        }
        System.out.println(Thread.currentThread().getName()+" realse read lock");
    }
    public void write(){
        synchronized (MUTEX_WRITE){
            System.out.println(Thread.currentThread().getName()+" get write lock");
            synchronized (MUTEX_READ){
                System.out.println(Thread.currentThread().getName()+" get read lock");
            }
            System.out.println(Thread.currentThread().getName()+" release read lock");
        }
        System.out.println(Thread.currentThread().getName()+" realse write lock");
    }
    public static void main(String[] args) {
        final DeadLock d1=new DeadLock();
        new Thread(()->{
            while (true){
                d1.read();
            }
        },"readThread").start();
        new Thread(()->{
            while (true){
                d1.write();
            }
        },"writeThread").start();
    }
}
