package com.designpattern.abstractfactory;

/**
 * Author Bryan.C <br>
 * Date 2018/5/1
 */
public interface HumanFactory {
    Human createYellowHuman();

    Human createBlackHuman();

    Human createWhiteHuman();
}
