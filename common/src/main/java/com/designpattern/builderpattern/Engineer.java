package com.designpattern.builderpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Bryan.C <br>
 * Date 2018/5/3
 */
public class Engineer {
    private List<String> sequence=new ArrayList<>();
    private BenzBuilder benzBuilder=new BenzBuilder();
    private BMWBuilder bmwBuilder=new BMWBuilder();

    public BenzModel getABenzModel(){
        this.sequence.clear();
        this.sequence.add("engineBoom");
        this.sequence.add("start");
        this.sequence.add("alarm");
        this.sequence.add("stop");
        this.benzBuilder.setSequence(this.sequence);
        return (BenzModel)this.benzBuilder.getCardModel();
    }

    public BenzModel getBBenzModel(){
        this.sequence.clear();
        this.sequence.add("engineBoom");
        this.sequence.add("alarm");
        this.sequence.add("start");
        this.sequence.add("stop");
        this.benzBuilder.setSequence(this.sequence);
        return (BenzModel)this.benzBuilder.getCardModel();
    }
}
