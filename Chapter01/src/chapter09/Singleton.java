package chapter09;

public class Singleton {

    //将2换到1,会产生不同的输出结果
    //①
    private static int x = 0;
    private static int y;
    private static Singleton instance = new Singleton();//②
    private Singleton( )
    {
        x++;
        y++;
    }
    public static Singleton getInstance( )
    {
        return instance;
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.x);
        System.out.println(singleton.y);
    }
}
