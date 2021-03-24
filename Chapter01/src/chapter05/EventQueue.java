package chapter05;

import java.util.LinkedList;

import static java.lang.Thread.currentThread;

public class EventQueue {
    private final int max;
    static class Event{}
    private final LinkedList<Event> eventQueue=new LinkedList<>();
    private final static int maxEvent=10;
    public EventQueue(){
        this(maxEvent);
    }
    public EventQueue(int max){
        this.max=max;
    }
    public void offer(Event event){
        synchronized (eventQueue){
            if (eventQueue.size()>=max){
                try {
                    console(" the queue is full");
                    eventQueue.wait();//线程会被阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console(" the new event is submitted");
            eventQueue.addLast(event);
            eventQueue.notify();//唤醒曾经执行monitor的wait方法而进入阻塞的线程
        }
    }
    public Event take(){
        synchronized (eventQueue){
            if (eventQueue.isEmpty()) {
                try {
                    console(" the  queue is empty");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event=eventQueue.removeFirst();
            eventQueue.notify();
            console(" the event "+event+" is handled.");
            return event;
        }
    }

    private void console(String s) {
        System.out.printf("%s:%s\n",currentThread().getName(),s);
    }
}
