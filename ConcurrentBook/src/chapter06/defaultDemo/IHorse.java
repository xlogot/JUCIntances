package chapter06.defaultDemo;

public interface IHorse {
    void est();
    default void run(){
        System.out.println("horse run");
    }
}
