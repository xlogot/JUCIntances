package chapter06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

public class PrimeUtil {
    public static boolean isPrime(int number){
        int tmp=number;
        if (tmp<2){
            return false;
        }
        for (int i=2;Math.sqrt(tmp)>=i;i++){
            if (tmp%i==0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {


        long l = System.currentTimeMillis();
        IntStream.range(1,1000000).filter(PrimeUtil::isPrime).count();//串行代码
        long m=System.currentTimeMillis();
        System.out.println(( m- l));
        IntStream.range(1,1000000).parallel().filter(PrimeUtil::isPrime).count();//并行代码
        long n=System.currentTimeMillis();
        System.out.println(( n-m));

        List<Integer> ss=new ArrayList<>();
        double asDouble = ss.stream().mapToInt(Integer::intValue).average().getAsDouble();
        ss.parallelStream().mapToInt(Integer::intValue).average().getAsDouble();
        int[] array=new int[10000];
        Arrays.parallelSort(array);
    }
}
