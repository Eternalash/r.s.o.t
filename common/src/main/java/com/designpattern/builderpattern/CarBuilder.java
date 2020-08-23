package com.designpattern.builderpattern;

import java.util.List;

/**
 * Author Bryan.C <br>
 * Date 2018/5/3
 */
public abstract class CarBuilder {
    public abstract void setSequence(List<String> sequence);
    public abstract CardModel getCardModel();
}
