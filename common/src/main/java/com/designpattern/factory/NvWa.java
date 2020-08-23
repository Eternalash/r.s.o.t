package com.designpattern.factory;

import com.designpattern.abstractfactory.FemaleFactory;
import com.designpattern.abstractfactory.MaleFactory;

/**
 * Author Bryan.C <br>
 * Date 2018/5/1
 */
public class NvWa {
    public static void main(String[] args){
        Factory yinYangLu=new HumanFactory();
        Human whiteHuman=yinYangLu.createHuman(WhiteHuman.class);
        whiteHuman.getColor();
        whiteHuman.talk();
        Human blackHuman=yinYangLu.createHuman(BlackHuman.class);
        blackHuman.getColor();
        blackHuman.talk();
        Human yellowHuman=yinYangLu.createHuman(YellowHuman.class);
        yellowHuman.getColor();
        yellowHuman.talk();

        com.designpattern.abstractfactory.HumanFactory yinYangLuMale=new MaleFactory();
        com.designpattern.abstractfactory.HumanFactory yinYangLuFemale=new FemaleFactory();
        com.designpattern.abstractfactory.Human maleBlackHuman = yinYangLuMale.createBlackHuman();
        com.designpattern.abstractfactory.Human femaleBlackHuman = yinYangLuFemale.createBlackHuman();
        maleBlackHuman.getColor();
        maleBlackHuman.talk();
        maleBlackHuman.getSex();
        femaleBlackHuman.getColor();
        femaleBlackHuman.talk();
        femaleBlackHuman.getSex();
    }
}
