package com.kalyp.buildingblocks.archunit.model.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@ConfigurationProperties(prefix = "health")
public class HealthServiceClientConfiguration {

    private static final String SERVICE_ID = "health-service";

    public HealthServiceClientConfiguration() {

    }

    @Bean
    BrainHealthApi brainHealthApi() {
        return new BrainHealthApi();
    }

    @Bean
    BodyHealthApi bodyHealthApi() {
        return new BodyHealthApi();
    }

    private static class BrainHealthApi {}
    private static class BodyHealthApi {}
}
