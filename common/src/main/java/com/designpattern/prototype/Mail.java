package com.designpattern.prototype;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Bryan.C <br>
 * Date 2018/5/7
 */
@Setter
@Getter
public class Mail implements Cloneable {
    private String receiver;
    private String subject;
    private String appellation;
    private String context;
    private String tail;
    private List<String> advertisement = new ArrayList<>();

    Mail(AdvTemple advTemple) {
        this.setContext(advTemple.ADV_SUBJECT);
        this.setSubject(advTemple.ADV_CONEXT);
    }

    @Override
    public Mail clone() {
        Mail mail = null;
        try {
            /*只拷贝对象本身 对期内数组 引用对象等都不拷贝*/
            mail = (Mail) super.clone();
            /*此处对引用类型进行拷贝*/
            List<String> advertisement = new ArrayList<>(this.advertisement);
            mail.setAdvertisement(advertisement);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return mail;
    }
}
