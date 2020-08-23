package com.personal.bryan.comm;

/**
 * Author: Bryan.C <br>
 * Date: 2018/10/15 11:30
 */
@AnnotationTest(sort = 2)
public class AnnotationTest1  implements AnnotationInterface{
    @Override
    @AopTest
    public void  print(String name){
        System.out.println(name+1);
    }

}
