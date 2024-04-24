package com.kalyp.buildingblocks.archunit.test;

import com.kalyp.buildingblocks.archunit.NamingConventionRules;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/**
 * Class declaring all naming convention rules as tests for easy adoption in services.
 */
public class AllNamingConventionRules {

    @ArchTest
    ArchRule controllersShouldBeSuffixed = NamingConventionRules.CONTROLLERS_SHOULD_BE_SUFFIXED;

    @ArchTest
    ArchRule servicesShouldHaveServiceInName = NamingConventionRules.SERVICES_SHOULD_HAVE_SERVICE_IN_NAME;

    @ArchTest
    ArchRule mappersShouldBeSuffixed = NamingConventionRules.MAPPERS_SHOULD_BE_SUFFIXED;

    @ArchTest
    ArchRule configurationClassesShouldBeSuffixed = NamingConventionRules.CONFIGURATION_CLASSES_SHOULD_BE_SUFFIXED;

    @ArchTest
    ArchRule repositoryClassesShouldBeSuffixed = NamingConventionRules.REPOSITORY_CLASSES_SHOULD_BE_SUFFIXED;

    @ArchTest
    ArchRule actuatorEndpointClassesShouldBeSuffixed =
        NamingConventionRules.ACTUATOR_ENDPOINT_CLASSES_SHOULD_BE_SUFFIXED;

    @ArchTest
    ArchRule eventHandlerClassesShouldBeSuffixed = NamingConventionRules.EVENT_HANDLER_CLASSES_SHOULD_BE_SUFFIXED;
}
