package chapter2;

public class BadLockOnInteger implements Runnable {
    public static Integer i=0;
    Object lock=new Object();
    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
//            synchronized (i){
                i++;
//            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BadLockOnInteger badLockOnInteger = new BadLockOnInteger();
        Thread t1=new Thread(badLockOnInteger);
        Thread t2=new Thread(badLockOnInteger);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
