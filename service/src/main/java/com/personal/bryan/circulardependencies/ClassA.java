package com.personal.bryan.circulardependencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author:bryan.c
 * Date:2021/6/8
 */
@Component
public class ClassA {
    @Autowired
    private ClassB classB;
}
