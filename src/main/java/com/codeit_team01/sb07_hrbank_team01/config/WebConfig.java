package com.codeit_team01.sb07_hrbank_team01.config;

import com.codeit_team01.sb07_hrbank_team01.interceptor.ClientIpInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Autowired
  private ClientIpInterceptor clientIpInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(clientIpInterceptor)
        .addPathPatterns("/**"); // 모든 경로에 적용
  }
}
