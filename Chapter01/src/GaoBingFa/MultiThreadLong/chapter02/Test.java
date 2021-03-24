package GaoBingFa.MultiThreadLong.chapter02;

public class Test {
    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            while (true){
                Thread.yield();
            }
        });
        t1.start();
        t1.interrupt();
    }
}
