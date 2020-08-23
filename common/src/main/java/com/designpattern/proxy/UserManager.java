package com.designpattern.proxy;

/**
 * Author Bryan.C <br>
 * Date 2018/5/6
 */
public interface UserManager {
    public void addUser(String userId,String userName);
    public void modifyUser(String userId,String userName);
    public void delUser(String userId);
    public String findUser(String userId);
}
