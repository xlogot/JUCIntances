package chapter05.StreamCountDemo;

public class PSStreamMain {
    public static void main(String[] args) {
        new Thread(new Plus()).start();
        new Thread(new Multiply()).start();
        new Thread(new Div()).start();
//        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                Msg msg = new Msg();
                msg.i=i;
                msg.j=j;
                msg.orgStr="result" ;
                Plus.bq.add(msg);
            }
        }
    }
    //冒泡排序.
    public static void bubbleSort(int[] array){
        for (int i=array.length-1;i>=0;i--){
            for (int j=0; j<i;j++){
                //降序排列
                if (array[j]<array[j+1]){
                    int temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
        }
    }
}
