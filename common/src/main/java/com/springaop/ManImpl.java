package com.springaop;



import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

/**
 *
 * Author Bryan.C <br>
 * Date 2017/1/5
 */
@Component("superman")
@Scope(value = "singleton")
public class ManImpl implements PersonService {
    @Size(min=1,max = 4,message = "长度超限")
    @NotNull(message = "姓名不能为空")
    public String name;
    @Size(min=0)
    public String age;
    @Valid
    public List<String> list;
    @Override
    public int sleep(int start,int end) {
        System.out.println("该睡觉了");
        return end-start;
    }
}
