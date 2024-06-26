package xyz.clavis.security.endpointsconfiguration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class ClavisHeaderInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String currentClientID = request.getHeader("X-APP-ID");

    if (currentClientID == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("X-APP-ID header is missing");
      return false;
    } else {
      return true;
    }

  }

}
