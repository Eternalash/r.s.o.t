package com.designpattern.proxy;

/**
 * Author Bryan.C <br>
 * Date 2018/5/6
 */
public class UserManagerImpl implements UserManager {
    /**
     * 添加用户
     */
    @Override
    public void addUser(String userId, String userName) {
        System.out.println("正在添加用户,用户为：" + userId + userName + "……");
    }

    /**
     * 删除用户
     */
    @Override
    public void delUser(String userId) {
        System.out.println("delUser,userId=" + userId);
    }

    /**
     * 查找用户
     */
    @Override
    public String findUser(String userId) {
        System.out.println("findUser,userId=" + userId);
        return userId;
    }

    @Override
    public void modifyUser(String userId, String userName) {
        System.out.println("modifyUser,userId=" + userId);
    }
}
