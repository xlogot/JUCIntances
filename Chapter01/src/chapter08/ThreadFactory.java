package chapter08;

@FunctionalInterface
public interface ThreadFactory {
    //用于创建线程的工厂.
    Thread createThread(Runnable runnable);
}
