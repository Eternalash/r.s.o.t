package com.designpattern.builderpattern;

import java.util.List;

/**
 * Author Bryan.C <br>
 * Date 2018/5/3
 */
public class BenzBuilder extends CarBuilder {
    private BenzModel benzModel=new BenzModel();
    @Override
    public void setSequence(List<String> sequence) {
        this.benzModel.setSequence(sequence);
    }

    @Override
    public CardModel getCardModel() {
        return this.benzModel;
    }
}
