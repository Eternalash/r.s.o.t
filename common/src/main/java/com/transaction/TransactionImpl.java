package com.transaction;

import org.springframework.transaction.annotation.Transactional;

/**
 * Author Bryan.C <br>
 * Date 2018/6/1 17:34
 */

public class TransactionImpl implements TransactionService {

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void transation(People people) throws Exception {
    people.setName("transaction");
  }
}
