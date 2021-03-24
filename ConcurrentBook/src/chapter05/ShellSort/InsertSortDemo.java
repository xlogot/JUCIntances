package chapter05.ShellSort;

import java.nio.channels.SocketChannel;
import java.util.jar.JarEntry;

public class InsertSortDemo {
    static void insertSort(int[] array){
        int length=array.length;
        int moveIndex,index,key;
        for (index = 1;  index<length ; index++) {
            key=array[index];
            moveIndex=index-1;
            while (moveIndex>=0&&array[moveIndex]>key){
                array[moveIndex+1]=array[moveIndex];
                moveIndex--;
            }
            array[moveIndex+1]=key;
        }
    }

    static void shellSort(int[] array){
//        SocketChannel
        int h=1;
        while (h<=array.length/3){
            h=h*3+1;
        }
        while (h>0){
            //插入排序
            for (int i = h; i < array.length; i++) {
                if (array[i]<array[i-h]){
                    int temp=array[i];
                    int j=i-h;
                    while (j>=0&&array[j]>temp){
                        array[j+h]=array[j];
                        j-=h;
                    }
                    array[j+h]=temp;
                }
            }
            h=(h-1)/3;
        }
    }
}
