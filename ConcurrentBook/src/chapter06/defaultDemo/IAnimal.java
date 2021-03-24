package chapter06.defaultDemo;

public interface IAnimal {
    default void breath(){
        System.out.println("breath");
    }
}
