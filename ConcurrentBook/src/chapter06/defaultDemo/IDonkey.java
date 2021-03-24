package chapter06.defaultDemo;

public interface IDonkey {
    default void run(){
        System.out.println("horse run");
    }
}
