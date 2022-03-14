package com.example.logbacktest;

import java.util.LinkedHashMap;
import java.util.Map;

public class MDCProps {

  private static final InheritableThreadLocal<String> context = new InheritableThreadLocal<>();

  private MDCProps() {}

  public static void setMdc(final String key, final String val) {
    context.set(key);
  }

  public static String getTenant() {
    return context.get();
  }

  public static void clear() {
    context.remove();
  }

}
