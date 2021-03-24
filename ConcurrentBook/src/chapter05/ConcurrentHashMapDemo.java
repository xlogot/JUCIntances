package chapter05;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    static void demo1(){
        ConcurrentHashMap<String,Integer> map=new ConcurrentHashMap<>();
        for (int i = 0; i < 1; i++) {
            map.put(Integer.toString(i),i);
        }
        //第一个参数表示并行度.
        int count=map.reduceValues(2,(i,j)->j);
        System.out.println(count);
    }

    //检查map中是否存在key, 如果不存在key创建这个key
    public static Integer getOrCreate(ConcurrentHashMap<String,Integer> map,String key){
        return map.computeIfAbsent(key, Integer::new);
    }
    public static Integer search(ConcurrentHashMap<String,Integer> map,String key){
        Integer found=map.search(2,(string,i)->{
            if (i%25==0) return i;
            return null;});
        return found;
    }
    public static void main(String[] args) {
        demo1();
    }
}
