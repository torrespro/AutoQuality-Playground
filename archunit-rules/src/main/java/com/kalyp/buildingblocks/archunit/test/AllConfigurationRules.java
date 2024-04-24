package com.kalyp.buildingblocks.archunit.test;

import com.kalyp.buildingblocks.archunit.ConfigurationRules;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/**
 * Class declaring all configuration rules as tests for easy adoption in services.
 */
public class AllConfigurationRules {

    @ArchTest
    ArchRule configurationClassesShouldBeUnderKalypKey =
        ConfigurationRules.CONFIGURATION_CLASSES_SHOULD_BE_UNDER_KALYP_KEY;

    @ArchTest
    ArchRule configurationClassesShouldBeValidated = ConfigurationRules.CONFIGURATION_CLASSES_SHOULD_BE_VALIDATED;

    @ArchTest
    ArchRule configurationClassesShouldBeContextScoped =
        ConfigurationRules.CONFIGURATION_CLASSES_SHOULD_BE_CONTEXT_SCOPED;

    @ArchTest
    ArchRule apiClientBeansShouldBeContextScoped = ConfigurationRules.API_CLIENT_BEANS_SHOULD_BE_CONTEXT_SCOPED;
}
