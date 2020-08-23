package com.designpattern.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Bryan.C <br>
 * Date 2018/5/9
 */
public abstract class Mediator {
    /**用于添加储存 "房东"角色*/
    protected List<Colleague> landlordList = new ArrayList<Colleague>();

    /**用于添加储存 "求租者"角色*/
    protected List<Colleague> renterList = new ArrayList<Colleague>();

    /**
     * 中介者注册房东信息
     * @param landlord 房东实体
     */
    public void registerLandlord(Colleague landlord){
        landlordList.add(landlord);
    }

    /**
     * 中介者注册 求租者信息
     * @param landlord 房东实体
     */
    public void registerRenter(Colleague landlord){
        renterList.add(landlord);
    }


    /**
     * 声明抽象方法 由具体中介者子类实现 消息的中转和协调
     */
    public abstract void operation(Colleague person, String message);
}
