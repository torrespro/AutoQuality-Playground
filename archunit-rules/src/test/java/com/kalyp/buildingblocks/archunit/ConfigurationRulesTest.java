package com.kalyp.buildingblocks.archunit;

import com.kalyp.buildingblocks.archunit.model.configuration.GameOfLifeConfig;
import com.kalyp.buildingblocks.archunit.model.configuration.HealthServiceClientConfiguration;
import com.kalyp.buildingblocks.archunit.model.configuration.LifeMeaningConfiguration;
import org.junit.jupiter.api.Test;

class ConfigurationRulesTest extends AbstractRulesTest {

    @Test
    void testConfigurationClassesShouldBeUnderKalypKeyWithAnnotationValue() {
        testRule(ConfigurationRules.CONFIGURATION_CLASSES_SHOULD_BE_UNDER_KALYP_KEY, GameOfLifeConfig.class, 2);
    }

    @Test
    void testConfigurationClassesShouldBeUnderKalypKeyWithAnnotationPrefix() {
        testRule(ConfigurationRules.CONFIGURATION_CLASSES_SHOULD_BE_UNDER_KALYP_KEY,
            HealthServiceClientConfiguration.class, 2);
    }

    @Test
    void testConfigurationClassesShouldBeValidated() {
        testRule(ConfigurationRules.CONFIGURATION_CLASSES_SHOULD_BE_VALIDATED, LifeMeaningConfiguration.class, 1);
    }

}
