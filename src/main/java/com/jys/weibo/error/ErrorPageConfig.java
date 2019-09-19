package com.jys.weibo.error;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorPageConfig {
    @Bean
    public WebServerFactoryCustomizer containerCustomizer(){
       return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
           @Override
           public void customize(ConfigurableWebServerFactory factory) {
               ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND,"/404.html");
               factory.addErrorPages(error404Page);
           }
       };
    }
}
