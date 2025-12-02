package com.codeit_team01.sb07_hrbank_team01.config;

import com.codeit_team01.sb07_hrbank_team01.interceptor.ClientIpInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private final ClientIpInterceptor clientIpInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(clientIpInterceptor)
        .addPathPatterns("/api/backups");
  }
}
