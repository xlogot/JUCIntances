package chapter05.SimpleFuture;


//FutureData是Future的模式的关键, 它实际上是真实数据RealData的代理
public class FutureData implements Data {
    protected RealData realData=null;
    protected boolean isReady=false;
    public synchronized void setRealData(RealData realData){
        if (isReady){
            return;
        }
        this.realData=realData;
        isReady=true;
        notifyAll();
    }
    @Override
    public synchronized String getResult(){
        while (!isReady){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getResult();
    }
}
