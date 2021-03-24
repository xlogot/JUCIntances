package chapter12;

import java.util.concurrent.TimeUnit;

public class VolatileFoo {

    final static int MAX=200;

    static volatile int init_value=0;

    public static void main(String[] args) {
        new Thread(()->{
            int localValue=init_value;//1
            while (localValue<MAX){
                if (init_value!=localValue){//4,7,
                    System.out.printf("[%d] \n",init_value);//3,8
                    //对localValue进行重新赋值
                    localValue=init_value;//4,100
                }
            }

        },"reader").start();

        new Thread(()->{
            int localValue=init_value;
            while (localValue<MAX){

                    System.out.printf("the init_value will be changed to %d \n",++localValue);
                    //对localValue进行重新赋值
                    init_value=localValue;
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

            }
        },"writer").start();

    }
}
