package com.example.logbacktest;

import ch.qos.logback.access.PatternLayout;
import ch.qos.logback.access.PatternLayoutEncoder;

public class CustomLayout extends PatternLayoutEncoder {
  @Override
  public void start() {
    PatternLayout layout = new PatternLayout();
    System.out.println("Putting converter");
    layout.getDefaultConverterMap().put("mdc", CustomConverter.class.getName());
    System.out.println("Effective converter map");
    layout.getEffectiveConverterMap().forEach((key, value) -> System.out.println(key + " ====>" + value));
    layout.setContext(context);
    layout.setPattern(getPattern());
    layout.start();
    this.layout = layout;
    super.start();
  }
}
