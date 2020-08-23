package com.designpattern.factory;

/**
 * Author Bryan.C <br>
 * Date 2018/5/1
 */
public class HumanFactory extends Factory {
    @Override
    public <T extends Human> T createHuman(Class<T> c) {
        Human human=null;
        try{
            human=(Human)Class.forName(c.getName()).newInstance();
        }
        catch (Exception e){
            System.out.println("泥人烧毁了!");
        }
        return (T)human;
    }
}
