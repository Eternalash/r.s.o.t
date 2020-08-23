package com.concurrency;

import lombok.Getter;
import lombok.Setter;

/**
 * Author Bryan.C <br>
 * Date 2018/6/27 14:53
 */
@Setter
@Getter
public class BankCard {
  private String cardid = "XZ456789";
  private int balance = 10000;
}
