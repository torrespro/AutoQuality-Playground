package com.kalyp.buildingblocks.archunit;

import com.kalyp.buildingblocks.archunit.model.service.HealthEngineImpl;
import com.kalyp.buildingblocks.archunit.model.service.LifeServiceImpl;
import org.junit.jupiter.api.Test;

class LoggingRulesTest extends AbstractRulesTest {

    @Test
    void testLoggersShouldBePrivateStaticFinal() {
        testRule(LoggingRules.LOGGERS_SHOULD_BE_PRIVATE_STATIC_FINAL, LifeServiceImpl.class, 2);
    }

    @Test
    void testServicesShouldHaveLogging() {
        testRule(LoggingRules.SERVICES_SHOULD_HAVE_LOGGING, HealthEngineImpl.class, 1);
    }

    @Test
    void testLoggingMessagesShouldBeParameterized() {
        testRule(LoggingRules.LOGGING_MESSAGES_SHOULD_BE_PARAMETERIZED, LifeServiceImpl.class, 1);
    }
}
