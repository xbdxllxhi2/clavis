package xyz.clavis.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "xyz.clavis.**")
public class LauncherApplication {

  public static void main(String[] args) {
    SpringApplication.run(LauncherApplication.class, args);
  }

}
