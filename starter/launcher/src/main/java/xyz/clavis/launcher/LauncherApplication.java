package xyz.clavis.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(basePackages = "xyz.clavis.**"
,excludeFilters = {@ComponentScan.Filter(pattern = "xyz.clavis.kafka.*", type = FilterType.REGEX)})
@EnableConfigurationProperties
public class LauncherApplication {

  public static void main(String[] args) {
    SpringApplication.run(LauncherApplication.class, args);
  }

}
