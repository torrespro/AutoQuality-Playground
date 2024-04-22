package com.kalyp.buildingblocks.archunit.test;

import com.kalyp.buildingblocks.archunit.LoggingRules;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/**
 * Class declaring all logging rules as tests for easy adoption in services.
 */
public class AllLoggingRules {

    @ArchTest
    ArchRule loggersShouldBePrivateStaticFinal = LoggingRules.LOGGERS_SHOULD_BE_PRIVATE_STATIC_FINAL;

    @ArchTest
    ArchRule servicesShouldHaveLogging = LoggingRules.SERVICES_SHOULD_HAVE_LOGGING;

    @ArchTest
    ArchRule loggingMessagesShouldBeParameterized = LoggingRules.LOGGING_MESSAGES_SHOULD_BE_PARAMETERIZED;
}
