package com.assignment.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
  private final BuildProperties buildProperties;

  @Bean
  public OpenAPI accountManagementModel(){
    return new OpenAPI()
        .info(new Info().title(SwaggerResources.ACCOUNT_MANAGEMENT));

  }

}
