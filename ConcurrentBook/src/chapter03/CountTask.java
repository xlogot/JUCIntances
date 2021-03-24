package chapter03;

import java.util.*;
import java.util.concurrent.*;

/*  CountTask继承自RecursiveTask类,可以携带返回值,
*
* */
public class CountTask extends RecursiveTask<Long>  {
    private static final int THRESHOLD=10000;
    private long start;
    private long end;
    public CountTask(long start,long end){
        this.start=start;
        this.end=end;
    }
    @Override
    protected Long compute() {
        long sum=0;
        boolean canCompute=(end-start)<THRESHOLD;
        if (canCompute){
            for (long i=start;i<=end;i++){
                sum+=i;
            }
        }else {
            //如果任务划分的层次很多,那么可能会导致任务迟迟得不到返回
            long step=(start+end)/10000;
            List<CountTask> subTasks=new ArrayList<>();
            long pos= start;
            for(int i=0;i<100;i++) {
                long lastOne=pos+step;
                if (lastOne>end) lastOne=end;
                CountTask subTask=new CountTask (pos, lastOne);
                pos+=step+1;
                subTasks . add (subTask) ;
                subTask. fork();
            }
            for (CountTask t: subTasks){
                sum+=t.join();
            }
        }
        return sum;
    }
    public static Map m=Collections.synchronizedMap(new HashMap<>());
    public static List<String> s=Collections.synchronizedList(new LinkedList<>());

    /*构造了一个1到200000的求和任务. 在第41行将任务提交给线程池, 线程池会返回一个携带结果的任务, 通过get()方法可以得到最终结果*/
    public static void main(String[] args) {
//
//        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        CountTask task = new CountTask (0, 200000L) ;
//        ForkJoinTask<Long> result = forkJoinPool.submit(task);
//        try{
//            long res = result.get();
//            System. out.println ("sum="+res);
//        }catch (InterruptedException e) {
//            e. printStackTrace();
//        }catch (ExecutionException e) {
//            e. printStackTrace();
//        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    throw new Exception("aaaaa");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
