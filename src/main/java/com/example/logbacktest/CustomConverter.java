package com.example.logbacktest;

import ch.qos.logback.access.pattern.AccessConverter;
import ch.qos.logback.access.spi.IAccessEvent;

public class CustomConverter extends AccessConverter {
  @Override
  public String convert(IAccessEvent iAccessEvent) {
    System.out.println("Convert from custom called");
    final String mdcPropertiesAsString = AccessLoggingMDC.getMdcPropertiesAsString();
    return mdcPropertiesAsString;
  }
}
