package com.cjemison.fetchrewards.config;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@Configuration
@ComponentScan(basePackages = {"com.cjemison.fetchrewards"})
@EnableSwagger2WebFlux
public class WebConfig {

  @Bean("threadPoolTaskExecutor")
  public Executor executor() {
    final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    threadPoolTaskExecutor.setMaxPoolSize(100);
    threadPoolTaskExecutor.setCorePoolSize(20);
    threadPoolTaskExecutor.setQueueCapacity(25000);
    return threadPoolTaskExecutor;
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.cjemison.fetchrewards.controller"))
        .paths(PathSelectors.any())
        .build();
  }
}
