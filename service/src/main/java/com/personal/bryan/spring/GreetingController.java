package com.personal.bryan.spring;

import com.personal.bryan.abstractservice.IRiskHandlerService;
import com.personal.bryan.comm.AnnotationService;
import com.personal.bryan.comm.Shouting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Author: Bryan.C <br>
 * Date: 2018/10/15 10:39
 */
@RestController
public class GreetingController {
  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @Autowired
  private AnnotationService annotationService;
  @Autowired
  private Shouting shouting;
  @Autowired
  private List<IRiskHandlerService> riskHandlerServiceList;

  @RequestMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    annotationService.print(name+shouting.process());
    return new Greeting(counter.incrementAndGet(), String.format(template, name));
  }

  @RequestMapping("/risk")
  public void risk() {
    riskHandlerServiceList.stream().filter(iRiskHandlerService -> iRiskHandlerService.getClass().getSimpleName().equalsIgnoreCase("RiskHandlerA")).forEach(IRiskHandlerService::doHandler);
    riskHandlerServiceList.forEach(IRiskHandlerService::doHandler);
  }


}
