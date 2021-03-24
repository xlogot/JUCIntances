import java.sql.DriverManager;
import java.sql.SQLException;

public class ProducerAndConsumer {
    public static void main(String[] args) throws SQLException {
//        Container container=new Container();
//        new Thread(new Producer(container)).start();
//        new Thread(new Consumer(container)).start();
        DriverManager.getConnection("111","111","111");
    }
}
class Producer implements Runnable {

    //produce a chicken
    Container container;

    public Producer(Container container) {
        this.container = container;
    }

    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            try {
                container.push(new Chicken(i));
                System.out.printf("produce %d chicken \n", i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Consumer implements Runnable{
    Container container;

    public Consumer(Container container) {
        this.container = container;
    }
    @Override
    public void run() {

            for (int i = 0; i < 100; i++) {
                try {
                    Chicken pop = container.pop();
                    System.out.printf("consume %d chicken......\n",pop.id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }
}

class Container{
    Chicken[] chickens=new Chicken[10];
    int count=0;

    //producer push one piece of chicken into the container
    public synchronized void push(Chicken chicken) throws InterruptedException {
        if (count==chickens.length){
            this.notifyAll();
            this.wait();
        }
            chickens[count]=chicken;
            count++;

    }

    public synchronized Chicken pop() throws InterruptedException {
        if (count==0){
            this.notifyAll();
            this.wait();
        }
        Chicken   chicken=chickens[count-1];
            count--;

        return chicken;
    }

}


class Chicken{
    int id;

    public Chicken(int id) {
        this.id = id;
    }
}