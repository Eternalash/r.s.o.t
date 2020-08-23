package com.designpattern.proxy;

/**
 * Author Bryan.C <br>
 * Date 2018/5/6
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("----已经保存数据!----");
    }
}
