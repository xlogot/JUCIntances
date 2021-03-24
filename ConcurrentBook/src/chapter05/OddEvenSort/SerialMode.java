package chapter05.OddEvenSort;

public class SerialMode {
    static void oddEvenSort(int[] array){
        int exchangeFlag=1,start=0;
        while (exchangeFlag==1||start==0){
            exchangeFlag=0;
            for (int i = start; i < array.length - 1; i+=2) {
                if (array[start]>array[start+1]){
                    int temp=array[start];
                    array[start]=array[start+1];
                    array[start+1]=temp;
                    exchangeFlag=1;
                }
            }
            if (start==0) start=1;
            else start=0;
        }
    }
}
