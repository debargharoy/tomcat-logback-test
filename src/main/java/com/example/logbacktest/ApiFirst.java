package com.example.logbacktest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiFirst {

  private static final Logger log = LoggerFactory.getLogger(ApiFirst.class);

  @GetMapping("hello")
  public String hello() {
    MDC.put("user_name", "test");
    log.info("This is INFO-level log");
    MDC.put("user_email", "test2");
    log.info("This is INFO-level 2 log");
    log.debug("This is DEBUG-level log");
    log.warn("This is WARN-level log");
    return "Hello";
  }

}
