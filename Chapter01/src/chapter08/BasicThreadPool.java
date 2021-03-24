package chapter08;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

//可以考虑使用组合的方式,替代继承thread类
public class BasicThreadPool extends Thread implements ThreadPool {

    //初始化线程数量
    private final int initSize;
    //线程池最大线程数量
    private final int maxSize;
    //线程池核心线程数量
    private final int coreSize;
    //当前活跃的线程数量
    private int activeCount;
    //创建线程所需的工厂
    private final ThreadFactory threadFactory;
    //任务队列
    private final RunnableQueue runnableQueue ;
    //线程池是否已经被shutdown
    private volatile boolean isShutdown = false ;
    //工作线程队列,存放活动线程
    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();
    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy .DiscardDenyPolicy( );
    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new  DefaultThreadFactory();
    private final long keepAliveTime ;
    private final TimeUnit timeUnit ;

    public BasicThreadPool(int initSize,int maxSize,int coreSize,int queueSize){
        this(initSize,maxSize,coreSize,DEFAULT_THREAD_FACTORY,queueSize,DEFAULT_DENY_POLICY,10,TimeUnit.SECONDS);
    }

    public BasicThreadPool(int initSize, int maxSize, int coreSize, ThreadFactory threadFactory,int queueSize,DenyPolicy denyPolicy, long keepAliveTime, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize,denyPolicy,this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }

    //初始化时, 先创建initSize个线程
    private void init() {
        start();
        for (int i = 0; i < initSize; i++) {
            newThread();
        }
    }
    //线程自动维护
    private void newThread() {
        //创建任务线程，并且启动
        InternalTask internalTask = new InternalTask ( runnableQueue);
        //从runnableQueue中取一个runnable(这个取的逻辑是在run方法中实现的,只有调用了thread 的start方法才能使用),再用这个runnable作为参数构建一个thread
        Thread thread = this.threadFactory.createThread ( internalTask);
        //将thread 和runnable任务进行绑定, 将绑定好的threadTask组合,放进队列.方便进行管理.
        ThreadTask threadTask = new ThreadTask (thread, internalTask) ;
        threadQueue . offer (threadTask) ;
        this.activeCount++;
        //调用internalTask的run()方法,从runnableQueue队列中取任务
        thread.start();
    }

    private void removeThread(){
        //从线程池中移除某个线程
        ThreadTask threadTask=threadQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount--;
    }

    @Override
    public void run() {
        while (!isShutdown && !isInterrupted()) {
            try {
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                isShutdown = true;
                break;
            }
            synchronized (this) {
                if (isShutdown) break;
                //当前的队列中有任务尚未处理，并且activeCount< coreSize则继续扩容
                if (runnableQueue.size() > 0 && activeCount < coreSize) {
                    for (int i = initSize; i < coreSize; i++) {
                        newThread();
                    }
                //continue的目的在于不想让线程的扩容直接达到maxsize
                    continue;
                }
                //当前的队列中有任务尚未处理，并且activeCount< maxSize则继续扩容
                if (runnableQueue.size() > 0 && activeCount < maxSize){
                    for (int i = coreSize; i < maxSize; i++) {
                        newThread();
                    }
                }
                //如果任务队列中没有任务，则需要回收，回收至coreSize即可
                if (runnableQueue.size() == 0 && activeCount > coreSize) {
                    for (int i = coreSize; i < activeCount; i++) {
                        removeThread();
                    }
                }
            }
        }
    }

    //threadTask只是internalTask和thread的一个组合
    private static class ThreadTask{
        Thread thread;
        InternalTask internalTask;

        public ThreadTask(Thread thread, InternalTask internalTask) {
            this.thread = thread;
            this.internalTask = internalTask;
        }
    }


    //将runnable插入runnableQueue中即可
    @Override
    public void execute(Runnable runnable) {
        if (this.isShutdown){
            throw new IllegalStateException("the thread pool is destroy");}
            //提交任务只是简单地往任务队列中插入runnable
            this.runnableQueue.offer(runnable);
    }

    @Override
    public void shutdown() {
        synchronized (this)
        {
            if ( isShutdown) return;
            isShutdown = true ;
            threadQueue . forEach ( threadTask ->{
                threadTask.internalTask.stop();
                threadTask. thread. interrupt( ) ;
        });
        this. interrupt( );
        }
    }

    @Override
    public int getInitSize() {
        if (isShutdown) throw new IllegalStateException("the thread pool is destroy");
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if (isShutdown) throw new IllegalStateException("the thread is destroy");
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        if (isShutdown) throw new IllegalStateException("the thread is destroy");
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if (isShutdown) throw new IllegalStateException("the thread is destroy");
        return runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
        synchronized (this){
            return this.activeCount;
        }
    }

    @Override
    public boolean isShutdown() {
        return this.isShutdown;
    }

    private static class DefaultThreadFactory implements ThreadFactory{

        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1) ;
        private static final ThreadGroup group=new ThreadGroup("MyThreadPool- "+GROUP_COUNTER.getAndDecrement());

        private static final AtomicInteger COUNTER=new AtomicInteger(9);

        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(group,runnable,"thread-pool-"+COUNTER.getAndDecrement());
        }
    }

}
