package util;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Author:bryan.c
 * Date:2021/5/26
 */
public class BeanUntilExample {
    @Data
    static class Man{
        private String name;
        private String address;
    }

    @Data
    static class Mankind{
        private String fullName;
        private String address;
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Man man=new Man();
        man.setName("man");
        man.setAddress("dsdsds");
        Mankind mankind=new Mankind();
        BeanUtils.copyProperties(man,mankind);
        System.out.println(mankind.getFullName());
        System.out.println(mankind.getAddress());

        List<String> list= Lists.newArrayList("a","b","c","d");
        List<String> list2= Lists.newArrayList("c","d");
        System.out.println(CollectionUtils.containsAny(Lists.newArrayList(list), list2));
        System.out.println(list.retainAll(list2));
        System.out.println(list);
    }
}
