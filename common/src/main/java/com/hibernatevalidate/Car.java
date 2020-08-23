package com.hibernatevalidate;

import javax.validation.constraints.*;

/**
 * Created by Bryan.C on 2017/1/9.
 */
public class Car {

  @NotNull
  private String manufacturer;

  @NotNull(message = "licensePlate 不能为空")
  @Size(min = 2, max = 14)
  private String licensePlate;

  @Min(2)
  private int seatCount;

  @AssertTrue
  private boolean isRegistered;

  public Car(@CannotContainSpaces String manufacturer, String licencePlate, int seatCount) {
    this.manufacturer = manufacturer;
    this.licensePlate = licencePlate;
    this.seatCount = seatCount;
    this.isRegistered = true;
  }

  //
  public void sellcar(@Pattern(regexp = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", message = "手机号格式不正确") String mobilephone) {
    System.out.println("车被卖给买家" + mobilephone);
  }
}
