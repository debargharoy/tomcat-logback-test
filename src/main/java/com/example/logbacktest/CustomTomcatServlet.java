package com.example.logbacktest;

import org.apache.catalina.Valve;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.lang.Nullable;

import javax.annotation.PostConstruct;

public class CustomTomcatServlet extends TomcatServletWebServerFactory {

  private final Valve valve;
  private final CustomEnvEntryProperties customEnvEntryProperties;

  public CustomTomcatServlet(
      @Nullable final Valve valve, final CustomEnvEntryProperties customEnvEntryProperties) {
    this.valve = valve;
    this.customEnvEntryProperties = customEnvEntryProperties;
  }

  @PostConstruct
  protected void init() {
    if (valve != null) {
      this.addContextValves(valve);
    }
    customEnvEntryProperties.setTomcatContexts(this);
  }

  @Override
  protected TomcatWebServer getTomcatWebServer(final Tomcat tomcat) {
    tomcat.enableNaming();
    return super.getTomcatWebServer(tomcat);
  }

}
