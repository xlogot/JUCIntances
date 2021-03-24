package chapter14;

//final 不允许被继承
public final class SingletonHungryMan {
    //实例变量
    private byte[] data=new byte[1024];

    //在定义实例变量对象的时候直接初始化
    private static SingletonHungryMan instance=new SingletonHungryMan();

    //私有构造函数, 不允许外部访问
    private SingletonHungryMan(){}

    public static SingletonHungryMan getInstance(){
        return instance;
    }
}
