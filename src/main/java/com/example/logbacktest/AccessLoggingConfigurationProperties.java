package com.example.logbacktest;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("access-logging")
public class AccessLoggingConfigurationProperties {
  private final List<String> ignoredUriPatterns = new ArrayList<>();

  private String pattern =
      "%h %l %u [%t{\"yyyy-MM-dd'T'HH:mm:ss,SSSXXX\", UTC}] \"%r\" %s %b \"%i{Referer}\" \"%i{User-Agent}\" \"%i{Host}\" \"%i{Forwarded}\" 0/%D 0 0 \"%i{Cerner-Correlation-ID}\"";

  private boolean enabled = true;
  private boolean logbackQuiet = true;

  public String getPattern() {
    return this.pattern;
  }

  public void setPattern(final String pattern) {
    this.pattern = pattern;
  }

  public boolean isEnabled() {
    return this.enabled;
  }

  public void setEnabled(final boolean enabled) {
    this.enabled = enabled;
  }

  public boolean isLogbackQuiet() {
    return this.logbackQuiet;
  }

  public void setLogbackQuiet(final boolean logbackQuiet) {
    this.logbackQuiet = logbackQuiet;
  }

  /**
   * URI patterns to be ignored on http requests. These are not simple regex patterns, but Ant-style
   * path patterns.
   *
   * <p>i.e. '/meta/**'
   *
   * @return list of uri patterns to ignore
   */
  public List<String> getIgnoredUriPatterns() {
    return ignoredUriPatterns;
  }
}
