package com.personal.bryan.comm;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Bryan.C <br>
 * Date: 2018/10/15 13:38
 */
@Service
public class AnnotationService implements AnnotationInterface {

  @Autowired
  private List<AnnotationInterface> validators;

  @Override
  public void print(String name) {
    validators.forEach(annotationInterface -> annotationInterface.print(name));
  }

  @Setter
  @Getter
  public class Pair {
    private AnnotationTest annotationTest;
    private AnnotationInterface annotationInterface;
  }
}
