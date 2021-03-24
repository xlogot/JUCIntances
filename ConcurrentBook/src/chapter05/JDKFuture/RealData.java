package chapter05.JDKFuture;

import java.util.concurrent.Callable;

public class RealData implements Callable<String> {
    private String para;

    public RealData(String para) {
        this.para = para;
    }

    //通过call方法构造真实需要的数据
    @Override
    public String call() throws Exception {
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        return sb.toString();
    }
}
