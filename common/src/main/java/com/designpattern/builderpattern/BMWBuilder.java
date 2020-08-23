package com.designpattern.builderpattern;

import java.util.List;

/**
 * Author Bryan.C <br>
 * Date 2018/5/3
 */
public class BMWBuilder extends CarBuilder {
    private BMWModel bmwModel=new BMWModel();
    @Override
    public void setSequence(List<String> sequence) {
        this.bmwModel.setSequence(sequence);
    }

    @Override
    public CardModel getCardModel() {
        return this.bmwModel;
    }
}
