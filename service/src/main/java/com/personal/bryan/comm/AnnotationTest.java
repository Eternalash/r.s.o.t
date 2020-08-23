package com.personal.bryan.comm;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * Author: Bryan.C <br>
 * Date: 2018/10/15 11:29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface AnnotationTest {
    int sort();
}
