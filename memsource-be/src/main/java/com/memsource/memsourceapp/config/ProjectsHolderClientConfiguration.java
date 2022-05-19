package com.memsource.memsourceapp.config;

import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectsHolderClientConfiguration {
    @Bean
    public OkHttpClient client(){
        return new OkHttpClient();
    }
}
