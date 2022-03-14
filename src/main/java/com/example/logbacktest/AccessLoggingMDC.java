package com.example.logbacktest;

import java.util.LinkedHashMap;
import java.util.Map;

public class AccessLoggingMDC {

  private static final ThreadLocal<Map<String, String>> mdcProperties = new ThreadLocal<>();

  public static void addMDCProperty(MDCProperty extraProperty) {
    final Map<String, String> mdc = mdcProperties.get();
    if (mdc == null) {
      Map<String, String > map = new LinkedHashMap<>();
      map.put(extraProperty.getName(), extraProperty.getValue());
      mdcProperties.set(map);
    } else {
      mdc.put(extraProperty.getName(), extraProperty.getValue());
      mdcProperties.set(mdc);
    }
  }

  public static String getMdcPropertiesAsString() {
    StringBuilder builder = new StringBuilder();
    System.out.println("Thread ID from AccessLoggingMDC: " + Thread.currentThread().getId());
    System.out.println("Thread Name from AccessLoggingMDC: " + Thread.currentThread().getName());
    mdcProperties.get().forEach((key, value) -> {
      builder.append(key).append(":");
      if (value.contains(" ")) {
        builder.append("\"").append(value).append("\"");
      } else {
        builder.append(value);
      }
      builder.append(" ");
    });
    return builder.toString();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    mdcProperties.get().forEach((key, value) -> {
      builder.append(key).append(":");
      if (value.contains(" ")) {
        builder.append("\"").append(value).append("\"");
      } else {
        builder.append(value);
      }
      builder.append(" ");
    });
    return builder.toString();
  }

  public static class MDCProperty {
    private String name;
    private String value;

    public MDCProperty() {}

    public MDCProperty(String name, String value) {
      this.name = name;
      this.value = value;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }
  }
  
}
