package com.designpattern.builderpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Bryan.C <br>
 * Date 2018/5/3
 * 封装性 建造者独立容易扩展 便于控制细节风险
 */
public class Client {
    public static void main(String[] args){
        List<String> sequence=new ArrayList<>();
        sequence.add("engineBoom");
        sequence.add("start");
        sequence.add("alarm");
        sequence.add("stop");
        BenzBuilder benzBuilder=new BenzBuilder();
        benzBuilder.setSequence(sequence);
        BenzModel benzModel=(BenzModel) benzBuilder.getCardModel();
        benzModel.run();

        Engineer engineer=new Engineer();
        engineer.getABenzModel().run();
    }
}
