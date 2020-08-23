package com;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author: Bryan.C <br>
 * Date:2020/3/6
 */
public class Main {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        System.out.println(LocalDateTime.now().format(formatter));
    }
}
