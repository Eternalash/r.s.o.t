package com.designpattern.mediator;

/**
 * Author Bryan.C <br>
 * Date 2018/5/9
 */
public class HouseMediator extends Mediator {
    @Override
    public void operation(Colleague person, String message) {
        if (person instanceof Renter) {
            // 将租屋的需求消息传递给 注册了的房东们
            for (Colleague landlord : landlordList) {
                landlord.getMessage(message);
            }
        } else if (person instanceof Landlord) {
            // 将房东的出租房消息传递给 注册了的 房屋求租者们
            for (Colleague renter : renterList) {
                renter.getMessage(message);
            }
        }

    }
}
