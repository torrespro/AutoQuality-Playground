package com.kalyp.buildingblocks.archunit;

import com.kalyp.buildingblocks.archunit.model.actuator.ArmEndpoint;
import com.kalyp.buildingblocks.archunit.model.configuration.GameOfLifeConfig;
import com.kalyp.buildingblocks.archunit.model.controller.HealthCheckerEndpoint;
import com.kalyp.buildingblocks.archunit.model.mapper.HealthIndicatorMapping;
import com.kalyp.buildingblocks.archunit.model.persistence.LifeStore;
import com.kalyp.buildingblocks.archunit.model.service.HealthEngineImpl;
import org.junit.jupiter.api.Test;

class NamingConventionRulesTest extends AbstractRulesTest {

    @Test
    void testControllersShouldBeSuffixed() {
        testRule(NamingConventionRules.CONTROLLERS_SHOULD_BE_SUFFIXED, HealthCheckerEndpoint.class, 1);
    }

    @Test
    void testServicesShouldHaveServiceInName() {
        testRule(NamingConventionRules.SERVICES_SHOULD_HAVE_SERVICE_IN_NAME, HealthEngineImpl.class, 1);
    }

    @Test
    void testMappersShouldBeSuffixed() {
        testRule(NamingConventionRules.MAPPERS_SHOULD_BE_SUFFIXED, HealthIndicatorMapping.class, 1);
    }

    @Test
    void testConfigurationClassesShouldBeSuffixed() {
        testRule(NamingConventionRules.CONFIGURATION_CLASSES_SHOULD_BE_SUFFIXED, GameOfLifeConfig.class, 1);
    }

    @Test
    void testRepositoryClassesShouldBeSuffixed() {
        testRule(NamingConventionRules.REPOSITORY_CLASSES_SHOULD_BE_SUFFIXED, LifeStore.class, 1);
    }

    @Test
    void testActuatorEndpointClassesShouldBeSuffixed() {
        testRule(NamingConventionRules.ACTUATOR_ENDPOINT_CLASSES_SHOULD_BE_SUFFIXED, ArmEndpoint.class, 1);
    }

}
