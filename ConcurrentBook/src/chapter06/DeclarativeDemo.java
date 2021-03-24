package chapter06;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntConsumer;
import java.util.logging.XMLFormatter;

public class DeclarativeDemo {
    public static void imperative(){
        int[] a={1,2,3,4,5};
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
    public static void declarative(){
//        Arrays.stream(a).forEach(System.out::println);
        int[] a={1,2,3,4,5};
        Arrays.stream(a).map((x)->x=x+1).forEach(System.out::println);
        Arrays.stream(a).forEach(System.out::println);
    }

    int[] a={1,3,4,5,6,7,8,9,10};
    public void isOdd(){
        for (int i = 0; i < a.length; i++) {
            if ((a[i]&2)!=2){
                a[i]++;
            }
            System.out.println(a[i]);
        }
        //使用函数式方式
        Arrays.stream(a).map(x-> (x&2)!=2 ? x++:x).forEach(System.out::println);

        Comparator<String> stringComparator = Comparator.comparingInt(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER);
//        IntConsumer
    }
}
