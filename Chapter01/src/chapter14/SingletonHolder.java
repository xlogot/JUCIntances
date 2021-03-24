package chapter14;

public class SingletonHolder {
    //实例变量
    private byte[] data=new byte[1024];
    private SingletonHolder(){}
    //静态内部类中持有Singleton的实例, 并且可被直接初始化
    private static class Holder{
        private static SingletonHolder instance=new SingletonHolder();
    }
    //调用getInstance方法, 事实上是获得Holder的Instance静态属性
    public static SingletonHolder getInstance(){
        return Holder.instance;
    }
}
