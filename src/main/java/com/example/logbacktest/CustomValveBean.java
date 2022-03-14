package com.example.logbacktest;

import ch.qos.logback.access.spi.IAccessEvent;
import ch.qos.logback.access.tomcat.LogbackValve;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

public class CustomValveBean extends LogbackValve {
  private static final Logger log = LoggerFactory.getLogger(CustomValveBean.class);

  private final AccessLoggingConfigurationProperties accessLoggingConfigurationProperties;

  public CustomValveBean(
      final AccessLoggingConfigurationProperties accessLoggingConfigurationProperties) {
    super();
    this.setQuiet(accessLoggingConfigurationProperties.isLogbackQuiet());
    this.accessLoggingConfigurationProperties = accessLoggingConfigurationProperties;
    this.setAsyncSupported(true);
  }

  @PostConstruct
  void construct() {
    final LoggerContext logCtx = (LoggerContext) LoggerFactory.getILoggerFactory();
    log.info("Logging format \n {}", accessLoggingConfigurationProperties.getPattern());


    final CustomLayout patternEncoder = new CustomLayout();
    patternEncoder.setContext(logCtx);
    log.info("Setting pattern on Encoder");
    patternEncoder.setPattern(accessLoggingConfigurationProperties.getPattern());
    log.info("Starting encoder");
    patternEncoder.start();

    final ConsoleAppender<IAccessEvent> appender = new ConsoleAppender<>();
    appender.setContext(logCtx);
    appender.setName("STDOUT");
    log.info("Setting pattern on Appender");
    appender.setEncoder(patternEncoder);
    appender.start();
    this.addAppender(appender);
    this.addFilter(new AccessFilterToMDC());
  }

  private class AccessFilterToMDC extends Filter<IAccessEvent> {

    @Override
    public FilterReply decide(final IAccessEvent event) {
      System.out.println("Thread ID: " + Thread.currentThread().getId());
      log.info("Setting MDC Properties");
      AccessLoggingMDC.addMDCProperty(new AccessLoggingMDC.MDCProperty("mdc1", "val1"));
      AccessLoggingMDC.addMDCProperty(new AccessLoggingMDC.MDCProperty("mdc2", "val2"));
      AccessLoggingMDC.addMDCProperty(new AccessLoggingMDC.MDCProperty("mdc3", "val 3"));
      return FilterReply.NEUTRAL;
    }
  }

}
