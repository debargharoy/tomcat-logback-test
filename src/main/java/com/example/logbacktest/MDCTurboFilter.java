package com.example.logbacktest;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.MDC;
import org.slf4j.Marker;

import java.util.LinkedHashMap;
import java.util.Map;

public class MDCTurboFilter extends TurboFilter {
  private final Map<String, String> extraProperties = new LinkedHashMap<>();

  @Override
  public FilterReply decide(
      Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
    extraProperties.forEach(MDC::put);
    return FilterReply.NEUTRAL;
  }

  public void addExtraProperty(ExtraProperty extraProperty) {
    extraProperties.put(extraProperty.getName(), extraProperty.getValue());
  }

  public static class ExtraProperty {
    private String name;
    private String value;

    public ExtraProperty() {}

    public ExtraProperty(String name, String value) {
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
