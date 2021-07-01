package jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * Author:bryan.c
 * Date:2021/5/26
 */
public class JOLExample {
    static A a;

    public static void main(String[] args) {
        a = new A();
        //打印JVM的详细信息
        System.out.println(VM.current().details());
        //打印对应的对象头信息
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}
