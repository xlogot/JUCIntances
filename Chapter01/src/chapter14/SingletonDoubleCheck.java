package chapter14;



public class SingletonDoubleCheck {
    //实例变量
    private byte[] data=new byte[1024];

    //在定义实例变量对象的时候直接初始化
    private static SingletonDoubleCheck instance=null;



    //私有构造函数, 不允许外部访问
    private SingletonDoubleCheck(){
        //初始化str

    }

    //这里实现了Double-Check
    public static SingletonDoubleCheck getInstance(){
        if (instance==null){
            synchronized (SingletonDoubleCheck.class){
                if (null==instance) {
                    instance = new SingletonDoubleCheck();
                }
            }
        }
        return instance;
    }
}
