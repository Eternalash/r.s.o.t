package com.designpattern.prototype;

import java.util.Random;

/**
 * Author Bryan.C <br>
 * Date 2018/5/7
 */
public class PrototypeClient {

    public static void main(String[] args) {
        sendMail();
    }

    private static void sendMail() {
        int i = 0;
        Mail mail = new Mail(new AdvTemple());
        mail.setTail("XX银行版权所有");
        int maxCount = 100;
        while (i < maxCount) {
            Mail mail1Clone = mail.clone();
            mail1Clone.setAppellation(getRandString(5) + "先生/女士");
            mail1Clone.setReceiver(getRandString(5) + "@" + getRandString(6) + ".com");
            mail1Clone.getAdvertisement().add("广告"+i);
            sendMail(mail1Clone);
            i++;
        }
    }

    private static void sendMail(Mail mail) {
        System.out.println("标题：" + mail.getSubject()+"\t广告:"+mail.getAdvertisement().size() + "\t收件人：" + mail.getReceiver() + "\t...发送成功！");
    }

    private static String getRandString(int maxLength) {
        String source = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder rs = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < maxLength; i++) {
            rs.append(source.charAt(random.nextInt(source.length())));
        }
        return rs.toString();
    }
}
