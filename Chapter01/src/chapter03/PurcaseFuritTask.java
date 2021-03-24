package chapter03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class PurcaseFuritTask extends Thread implements IPurchaseFruit {

    private String fruit;

    public PurcaseFuritTask(String fruit){
        this.fruit=fruit;
    }
    @Override
    public String get() {
       return this.fruit;
    }


    @Override
    public void run() {
        System.out.println(getName()+"go to the market");
        int val=ThreadLocalRandom.current().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(val);
            } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("["+getName()+"]"+"is purchasing "+fruit);
    }
}
