package com.personal.bryan.abstractservice;

import org.springframework.stereotype.Component;

/**
 * Author:bryan.c
 * Date:2021/5/28
 */
@Component
public class RiskHandlerB extends AbstractRiskHandler {
    @Override
    public void doHandler() {
        System.out.println("RiskHandlerB");
    }
}
