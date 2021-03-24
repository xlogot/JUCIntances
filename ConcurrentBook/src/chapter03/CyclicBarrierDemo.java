package chapter03;

import java.util.Random;
import java.util.concurrent.*;

public class CyclicBarrierDemo {
    public static class soldier implements Runnable{
        private String soldier;
        private final CyclicBarrier cyclic;

        public soldier(CyclicBarrier cyclic,String soldier) {
            this.soldier = soldier;
            this.cyclic = cyclic;
        }

        @Override
        public void run() {
            try {
                //这里体现了CyclicBarrier的复用
                cyclic.await();
                doWork();
                cyclic.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
        void doWork(){
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(10)*1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println((soldier + "mission complete"+Thread.currentThread().getName()));
        }

        public static class BarrierRun implements Runnable{
            boolean flag;
            int N;

            public BarrierRun(boolean flag, int n) {
                this.flag = flag;
                N = n;
            }

            @Override
            public void run() {
                if (flag){
                    System.out.println(Thread.currentThread().getName()+"========");
                    System.out.println("commander: "+N+" soldiers mission complete");
                }else {
                    System.out.println(Thread.currentThread().getName()+"========");
                    System.out.println("commander: "+N+" soldiers 集合完成");
                    flag=true;
                }
            }
        }

        public static void main(String[] args) {
            final  int N=10;
            Thread[] allSoldiers=new Thread[N];
            boolean flag=false;
            System.out.println(Thread.currentThread().getName()+"========");
            CyclicBarrier cyclic=new CyclicBarrier(N,new BarrierRun(false,N));
            System.out.println("队伍集合");
            for (int i = 0; i < N; i++) {
                System.out.println(i+"报道");
                allSoldiers[i] =new Thread(new soldier(cyclic,"soldier"+i));
                allSoldiers[i].start();
            }
        }
    }
}
