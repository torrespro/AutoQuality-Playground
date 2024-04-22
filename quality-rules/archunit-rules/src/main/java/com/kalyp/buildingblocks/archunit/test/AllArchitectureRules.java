package com.kalyp.buildingblocks.archunit.test;

import com.kalyp.buildingblocks.archunit.ArchitectureRules;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/**
 * Class declaring all Architecture rules as tests for easy adoption in services.
 */
public class AllArchitectureRules {

    @ArchTest
    ArchRule layeredArchitectureShouldBeAdopted = ArchitectureRules.LAYERED_ARCHITECTURE_SHOULD_BE_ADOPTED;

    @ArchTest
    ArchRule controllerClassesShouldBeInAppropriatePackage =
        ArchitectureRules.CONTROLLER_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE;

    @ArchTest
    ArchRule serviceClassesShouldBeInAppropriatePackage =
        ArchitectureRules.SERVICE_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE;

    @ArchTest
    ArchRule mapperClassesShouldBeInAppropriatePackage =
        ArchitectureRules.MAPPER_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE;

    @ArchTest
    ArchRule configurationClassesShouldBeInAppropriatePackage =
        ArchitectureRules.CONFIGURATION_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE;

    @ArchTest
    ArchRule repositoryClassesShouldBeInAppropriatePackage =
        ArchitectureRules.REPOSITORY_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE;

    @ArchTest
    ArchRule clientClassesShouldBeInAppropriatePackage =
        ArchitectureRules.CLIENT_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE;

    @ArchTest
    ArchRule actuatorEndpointClassesShouldBeInAppropriatePackage =
        ArchitectureRules.ACTUATOR_ENDPOINT_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE;

    @ArchTest
    ArchRule eventHandlerClassesShouldBeInAppropriatePackage =
        ArchitectureRules.EVENT_HANDLER_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE;

    @ArchTest
    ArchRule auditClassesShouldBeInAppropriatePackage =
        ArchitectureRules.AUDIT_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE;

    @ArchTest
    ArchRule servicesShouldBeSecured = ArchitectureRules.SERVICES_SHOULD_BE_SECURED;
}
