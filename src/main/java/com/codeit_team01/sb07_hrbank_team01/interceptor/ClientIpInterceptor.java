package com.codeit_team01.sb07_hrbank_team01.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ClientIpInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request,
      HttpServletResponse response, Object handler) throws Exception {

    String ip = extractClientIp(request);
    // 컨트롤러에서 사용할 수 있도록 request attribute에 저장
    request.setAttribute("clientIp", ip);

    return true;
  }

  private String extractClientIp(HttpServletRequest request) {
    String ip = request.getHeader("X-Forwarded-For");
    if (ip != null && !ip.isBlank() && !"unknown".equalsIgnoreCase(ip)) {
      return ip.split(",")[0].trim();
    }

    ip = request.getHeader("X-Real-IP");
    if (ip != null && !ip.isBlank() && !"unknown".equalsIgnoreCase(ip)) {
      return ip.trim();
    }

    return request.getRemoteAddr();
  }
}
