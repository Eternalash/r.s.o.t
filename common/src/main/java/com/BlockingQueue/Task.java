package com.BlockingQueue;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by Bryan.C on 2017/12/5.
 */
@Component
@Setter
@Getter
public class Task {
  private int no;
  public void test(){
    System.out.println(no);
  }
}
