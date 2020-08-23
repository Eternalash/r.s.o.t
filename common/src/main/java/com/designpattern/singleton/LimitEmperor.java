package com.designpattern.singleton;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Author Bryan.C <br>
 * Date 2018/5/1
 */
@Setter
@Getter
public class LimitEmperor {
    /**
     * 饿汉模式
     */
    private static final int MAX_NUM_OF_EMPEROR = 2;
    private static List<LimitEmperor> LIMIT_EMPEROR_LIST = new ArrayList<>();
    private static List<String> LIMIT_EMPEROR_NAME_LIST = new ArrayList<>();
    private static int CURRENT_NUM_OF_KING=0;
    static {
        for (int i = 0; i < MAX_NUM_OF_EMPEROR; i++) {
            LIMIT_EMPEROR_LIST.add(new LimitEmperor(+(i + 1) + "th king"));
        }
    }

    private LimitEmperor(String name) {
        LIMIT_EMPEROR_NAME_LIST.add(name);
    }

    public static LimitEmperor getSINGLETON() {
        Random random = new Random();
        CURRENT_NUM_OF_KING=random.nextInt(MAX_NUM_OF_EMPEROR);
        return LIMIT_EMPEROR_LIST.get(CURRENT_NUM_OF_KING);
    }

    public static void say() {
        System.out.println(LIMIT_EMPEROR_NAME_LIST.get(CURRENT_NUM_OF_KING) + " of the world!");
    }
}
