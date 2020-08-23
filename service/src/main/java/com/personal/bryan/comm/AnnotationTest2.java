package com.personal.bryan.comm;

/**
 * Author: Bryan.C <br>
 * Date: 2018/10/15 11:32
 */
@AnnotationTest(sort = 1)
public class AnnotationTest2 implements AnnotationInterface {
    @Override
    @AopTest
    public void  print(String name){
        System.out.println(name+2);
    }
}
