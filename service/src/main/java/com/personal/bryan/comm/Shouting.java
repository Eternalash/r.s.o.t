package com.personal.bryan.comm;


import org.springframework.stereotype.Component;

/**
 * Author: Bryan.C <br>
 * Date:2020/6/4
 */
@Component
public class Shouting extends BaseShouting {
    private static final Man MAN=new Man();
    @Override
    protected String shout(){
        return MAN.manName();
    }
}
