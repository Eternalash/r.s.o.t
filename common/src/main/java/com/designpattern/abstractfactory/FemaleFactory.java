package com.designpattern.abstractfactory;

/**
 * Author Bryan.C <br>
 * Date 2018/5/1
 */
public class FemaleFactory implements HumanFactory {
    @Override
    public Human createYellowHuman() {
        return null;
    }

    @Override
    public Human createBlackHuman() {
        return new FemalAbstractBlackHuman();
    }

    @Override
    public Human createWhiteHuman() {
        return null;
    }
}
