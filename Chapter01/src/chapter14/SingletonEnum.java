package chapter14;

public class SingletonEnum {
    private byte[] data=new byte[1024];
    private SingletonEnum(){}
    private enum EnumHolder{
        INSTANCE;
        private SingletonEnum instance;
        EnumHolder(){
            this.instance=new SingletonEnum();
        }
        private SingletonEnum getInstance(){
            return instance;
        }
    }
    public static SingletonEnum getInstance(){
        return EnumHolder.INSTANCE.getInstance();
    }
}
