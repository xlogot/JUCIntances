package chapter03;

public class InterruptThreadExit {
    public static void main(String[] args) {
        Thread t=new Thread(){
            @Override
            public void run() {
                while (!isInterrupted()){
                    //do something here;
                }
            }
        };
        t.start();
    }
}
