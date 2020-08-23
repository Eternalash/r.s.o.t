package structure;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author Bryan.C <br>
 * Date 2018/7/7
 */
public class DataBootstrap {
    public static void main(String[] args){
        HashMap hashMap=new HashMap(1);
        hashMap.put("B","B");
        hashMap.put("A","A");
        hashMap.put(33L,"a");
        hashMap.put("C","C");
        hashMap.put("D","D");

        ConcurrentHashMap concurrentHashMap=new ConcurrentHashMap(1);
    }
}
