package com.concurrent.chapter01;

public class ThreadConstructor {
    public static void main(String[] args) {
        Thread t1=new Thread("t1");

        ThreadGroup group=new ThreadGroup("testgroup");

        Thread t2=new Thread(group,"t2");

        ThreadGroup mianThreadGroup=Thread.currentThread().getThreadGroup();

        System.out.println(mianThreadGroup.getName()+" main thread belong");
        System.out.println("t1 and main belong to the one thread :"+ (mianThreadGroup==t1.getThreadGroup()));
        System.out.println("t2 and main belong to the one thread :"+ (mianThreadGroup==t2.getThreadGroup()));
        System.out.println("t1 and t2 belong to the one thread :"+ (t2.getThreadGroup()==t1.getThreadGroup()));
    }
}
