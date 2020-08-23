package com.hibernatevalidate;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by Bryan.C on 2017/1/9.
 */
public class ValidateFun {

  public static String validateModel(Object obj) { // 验证某一个对象

    StringBuffer buffer = new StringBuffer(64); // 用于存储验证后的错误信息

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);// 验证某个对象,，其实也可以只验证其中的某一个属性的

    for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
      String message = constraintViolation.getMessage();
      buffer.append(constraintViolation.getPropertyPath() + message + "\n");
    }
    return buffer.toString();
  }

  public static String validateMethod(Object obj, Method method, Object[] parameterValues) { // 验证某一个对象

    StringBuffer buffer = new StringBuffer(64); // 用于存储验证后的错误信息

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    ExecutableValidator executableValidator = validator.forExecutables();
    // executableValidator.validateConstructorParameters
    // executableValidator.validateConstructorReturnValue()
    // executableValidator.validateReturnValue()
    Set<ConstraintViolation<Object>> methodValidators =
        executableValidator.validateParameters(obj, method, parameterValues);

    for (ConstraintViolation<Object> constraintViolation : methodValidators) {
      String message = constraintViolation.getMessage();
      buffer.append(constraintViolation.getPropertyPath() + message + "\n");
    }

    return buffer.toString();
  }
}
