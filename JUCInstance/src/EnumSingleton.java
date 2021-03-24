public class EnumSingleton {
    private enum EnumHolder{
        INSTANCE;
        private EnumSingleton singleton;
        EnumHolder(){
            singleton=new EnumSingleton();
        }
        private EnumSingleton getSingleton(){
            return singleton;
        }
    }
    public static EnumSingleton getInstance(){
        return EnumHolder.INSTANCE.getSingleton();
    }
}
