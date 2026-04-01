package com.qode.springimpl;

import com.qode.springimpl.annotation.Bean;
import com.qode.springimpl.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public String testValue() {
        return "testValue";
    }

    @Bean(value = "useBeanValue")
    public String testValue2() {
        return "testValue2";
    }
}
