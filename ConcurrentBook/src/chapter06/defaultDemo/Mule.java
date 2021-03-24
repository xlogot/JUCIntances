package chapter06.defaultDemo;

public class Mule implements IHorse,IAnimal,IDonkey {
    @Override
    public void run() {
        IHorse.super.run();
    }

    @Override
    public void est() {
        System.out.println("mule eat");
    }
}
