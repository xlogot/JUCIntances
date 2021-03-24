package chapter06.StampedLockDemo;

import java.util.concurrent.locks.StampedLock;

public class Point {
    private double x, y;
    private final StampedLock sl = new StampedLock();
    void move(double deltaX, double deltaY) {   //这是一个排它锁
        long stamp = sl.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            sl.unlockWrite(stamp);
        }
    }
    double distanceFromOrigin() {       //只读方法
        long stamp = sl.tryOptimisticRead();//这个方法表示视图尝试一次乐观读,它会返回一个类似于时间戳. 作为这一次的锁获取的凭证.
        double currentX = x, currentY = y;
        if (!sl.validate(stamp)) {//如果乐观读失败,则进行悲观读, 也就是加锁
            stamp = sl.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
}