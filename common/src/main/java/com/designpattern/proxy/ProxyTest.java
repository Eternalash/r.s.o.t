package com.designpattern.proxy;

/**
 * Author Bryan.C <br>
 * Date 2018/5/6
 * 如果加入容器的目标对象有实现接口,用JDK代理
 *如果目标对象没有实现接口,用Cglib代理
 */
public class ProxyTest {
    public static void main(String[] args) {
        //region 静态代理
        UserDao target = new UserDao();
        //代理对象,把目标对象传给代理对象,建立代理关系
        UserDaoProxy proxy = new UserDaoProxy(target);
        proxy.save();//执行的是代理的方法
        //endregion


        //region 动态代理
        // 给目标对象，创建代理对象
        IUserDao proxydynamic = (IUserDao) new ProxyFactory(target).getProxyInstance();
        // class $Proxy0   内存中动态生成的代理对象
        System.out.println(proxydynamic.getClass());
        // 执行方法   【代理对象】
        proxydynamic.save();
        //endregion

        //region cglib 别名子类代理 故代理对象不能为final 方法不能为static 或final
        //代理对象
        UserDao proxyCglib = (UserDao)new ProxyFactoryCglib(target).getProxyInstance();
        //执行代理对象的方法
        proxyCglib.save();
        //endregion


    }
}
