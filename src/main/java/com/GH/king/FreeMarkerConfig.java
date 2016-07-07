package com.GH.king;

import freemarker.template.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import javax.inject.Inject;


/**
 * Created by alex on 2016/3/10.
 */

@org.springframework.context.annotation.Configuration
public class FreeMarkerConfig {

    @Inject
    private FreeMarkerConfigurer freeMarkerConfigurer;

    private Configuration configuration;

    @Bean
    public Configuration getConfiguration(){
        configuration = freeMarkerConfigurer.getConfiguration();
        return configuration;
    }
}
