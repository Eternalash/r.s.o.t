package com.personal.bryan.abstractservice;

/**
 * Author:bryan.c
 * Date:2021/5/28
 */
public abstract class AbstractRiskHandler implements IRiskHandlerService {
    public void doHandler(){
        System.out.println("AbstractRiskHandler");
    }
}
