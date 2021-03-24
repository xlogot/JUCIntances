package chapter14;

public class SingletonLazyMan {
    private byte[] data=new byte[1024];
    private static SingletonLazyMan instance=null;

    private SingletonLazyMan() {}

    public static SingletonLazyMan getInstance() {
        if (instance==null)
            instance=new SingletonLazyMan();
        return instance;
    }
}
