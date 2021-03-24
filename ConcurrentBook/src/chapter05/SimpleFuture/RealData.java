package chapter05.SimpleFuture;

public class RealData implements Data  {
    protected final String result;
    public RealData(String para){
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        result=sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
