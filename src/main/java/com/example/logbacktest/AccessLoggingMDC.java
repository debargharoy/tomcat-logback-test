package com.example.logbacktest;

import java.util.LinkedHashMap;
import java.util.Map;

public class AccessLoggingMDC {

  public static void addMDCProperty(MDCProperty extraProperty) {
    MDCProps.setMdc(extraProperty.getName(), extraProperty.getValue());
  }

  public static String getMdcPropertiesAsString() {
    StringBuilder builder = new StringBuilder();
    System.out.println("Thread ID from AccessLoggingMDC: " + Thread.currentThread().getId());
//    MDCProps.getTenant().forEach((key, value) -> {
//      builder.append(key).append(":");
//      if (value.contains(" ")) {
//        builder.append("\"").append(value).append("\"");
//      } else {
//        builder.append(value);
//      }
//      builder.append(" ");
//    });
//    return builder.toString();
    return MDCProps.getTenant();
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
