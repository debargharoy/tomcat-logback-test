package com.example.logbacktest;

import org.apache.catalina.core.StandardContext;
import org.apache.tomcat.util.descriptor.web.ContextEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("custom")
public class CustomEnvEntryProperties {

  private static final Logger log = LoggerFactory.getLogger(CustomEnvEntryProperties.class);

  private final List<ContextEnvironment> envEntryList = new ArrayList<>();

  public List<ContextEnvironment> getEnvEntry() {
    return envEntryList;
  }

  /**
   * Convenience method to quickly set a collection of `env-entry` values for Tomcat.
   *
   * @param factory {@link TomcatServletWebServerFactory}
   */
  public void setTomcatContexts(final TomcatServletWebServerFactory factory) {
    factory.addContextCustomizers(
        context ->
            envEntryList.forEach(
                envEntry -> {
                  if (envEntry.getType() == null || envEntry.getType().isEmpty()) {
                    envEntry.setType(String.class.getName());
                  }
                  context.getNamingResources().addEnvironment(envEntry);
                  log.debug("{}", envEntry);
                }),
        context -> ((StandardContext) context).setFailCtxIfServletStartFails(true));
  }
}
