package com.kalyp.buildingblocks.archunit.model.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@ConfigurationProperties("game.life")
public class GameOfLifeConfig {
}
