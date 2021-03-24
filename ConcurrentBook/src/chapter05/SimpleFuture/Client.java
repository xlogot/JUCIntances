package chapter05.SimpleFuture;

public class Client {
    public Data request(final String queryString){
        final FutureData future=new FutureData();
        new  Thread(){
            @Override
            public void run() {
                RealData realData = new RealData(queryString);
                future.setRealData(realData);
            }
        }.start();
        return future;
    }

    public static void main(String[] args) {
        Client client=new Client();
        Data data = client.request("name");
        System.out.println("请求完毕");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("data= "+ data.getResult());
    }
}
