package com.GH.king;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.models.dto.builder.ApiInfoBuilder;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

@Configuration
@EnableSwagger

/**
 * Created by alex on 2016/1/21.
 */
public class SwaggerUIConfig {
    @Inject
    private SpringSwaggerConfig springSwaggerConfig;
    @Bean
    public SwaggerSpringMvcPlugin APIConfiguration() {
        SwaggerSpringMvcPlugin plugin = new SwaggerSpringMvcPlugin(springSwaggerConfig);
        ApiInfo apiInfo = this.getApiInfo();

        plugin.apiInfo(apiInfo)
                .apiVersion("1.0")
                .includePatterns("/v1*.*");

        plugin.useDefaultResponseMessages(false);
        return plugin;
    }
    private ApiInfo getApiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("GaH")
                .description("GaH backend")
                .termsOfServiceUrl("http://example.com/terms-of-service")
                .contact("18817393346@163.com")
                .license("MIT License")
                .licenseUrl("http://opensource.org/licenses/MIT")
                .build();
        return apiInfo;
    }
}
