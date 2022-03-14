package com.example.logbacktest;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({
  AccessLoggingConfigurationProperties.class,
  CustomEnvEntryProperties.class
})
public class Configurator {

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(
      name = "server.tomcat.accesslog.enabled",
      havingValue = "false",
      matchIfMissing = true)
  public CustomValveBean httpAccessValveBean(
      @Nullable final AccessLoggingConfigurationProperties accessLoggingConfigurationProperties) {
    return new CustomValveBean(accessLoggingConfigurationProperties);
  }

  @Bean
  public ServletWebServerFactory servletWebServerFactory(
      final CustomValveBean httpAccessValveBean,
      final CustomEnvEntryProperties customEnvEntryProperties) {
    return new CustomTomcatServlet(
        httpAccessValveBean, customEnvEntryProperties);
  }

}
