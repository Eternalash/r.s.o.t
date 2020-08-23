package com.designpattern.builderpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Bryan.C <br>
 * Date 2018/5/3
 */
public abstract class CardModel {
    private List<String> sequence = new ArrayList<>();

    abstract void start();

    abstract void stop();

    abstract void alarm();

    abstract void engineBoom();

    final void run() {
        sequence.stream().forEach(s -> {
            if (s.equalsIgnoreCase("start")) {
                this.start();
            } else if (s.equalsIgnoreCase("stop")) {
                this.stop();
            } else if (s.equalsIgnoreCase("alarm")) {
                this.alarm();
            } else if (s.equalsIgnoreCase("engineBoom")) {
                this.engineBoom();
            }
        });
    }

    final void setSequence(List<String> sequence) {
        this.sequence = sequence;
    }
}
