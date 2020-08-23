package com.hibernatevalidate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * Created by Bryan.C on 2017/1/10.
 */
@Constraint(validatedBy = CannotContainSpacesValidator.class) //具体的实现
@Target({ElementType.METHOD,
    ElementType.PARAMETER,
    ElementType.FIELD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface CannotContainSpaces {

  String message() default "不能包含空格"; //提示信息,可以写死,可以填写国际化的key

  int length() default 5;

  //下面这两个属性必须添加
  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
