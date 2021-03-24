package chapter04;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

public class ThreadLocalDemo {
    static ThreadLocal<SimpleDateFormat> t1=new ThreadLocal<>();
    public static class  ParseDate implements Runnable{
        @Override
        public void run() {
            try {
                if (t1.get()==null){
                    t1.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
                Date t=t1.get().parse("20202020202020");
                System.out.println(t.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
