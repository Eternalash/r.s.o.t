package com.personal.bryan.spring;

/**
 * Author: Bryan.C <br>
 * Date: 2018/10/15 10:39
 */
public class Greeting {
    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
