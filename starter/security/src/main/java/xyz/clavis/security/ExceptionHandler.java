package xyz.clavis.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleException(Exception e) {
    log.error(e.getMessage());

    return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
