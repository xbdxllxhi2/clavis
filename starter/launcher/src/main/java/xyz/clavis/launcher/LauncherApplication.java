package xyz.clavis.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "xyz.clavis.**")
@EnableConfigurationProperties
public class LauncherApplication {

  public static void main(String[] args) {
    SpringApplication.run(LauncherApplication.class, args);
  }

}
