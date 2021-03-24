package com.concurrent.chapter01;

public class TemplatedMethod {
    public final void print(String message){
        System.out.println("######");
        wrapPrint(message);
        System.out.println("######");
    }

    protected void wrapPrint(String message) {
    }

    public static void main(String[] args) {
        TemplatedMethod t1=new TemplatedMethod(){
            @Override
            protected void wrapPrint(String message) {
                System.out.println("*"+message+"*");
            }
        };
        t1.print("Hello message");

        TemplatedMethod t2=new TemplatedMethod(){
            @Override
            protected void wrapPrint(String message) {
                System.out.println("`"+message+"`");
            }
        };
        t2.print("hello message");
    }
}
