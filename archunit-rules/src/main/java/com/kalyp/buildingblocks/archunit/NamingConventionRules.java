package com.kalyp.buildingblocks.archunit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.lang.ArchRule;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rules related to agreed naming conventions.
 */
public class NamingConventionRules {

    private NamingConventionRules() {
    }

    public static final ArchRule CONTROLLERS_SHOULD_BE_SUFFIXED = classes().that()
        .areAnnotatedWith(RestController.class)
        .should().haveSimpleNameEndingWith("Controller");

    public static final ArchRule SERVICES_SHOULD_HAVE_SERVICE_IN_NAME = classes().that()
        .areAnnotatedWith(Service.class)
        .should().haveSimpleNameContaining("Service");

    public static final ArchRule MAPPERS_SHOULD_BE_SUFFIXED = classes().that()
        .areAnnotatedWith("org.mapstruct.Mapper")
        .should().haveSimpleNameEndingWith("Mapper");

    public static final ArchRule CONFIGURATION_CLASSES_SHOULD_BE_SUFFIXED = classes().that()
        .areAnnotatedWith(Configuration.class)
        .should().haveSimpleNameEndingWith("Configuration");

    public static final ArchRule REPOSITORY_CLASSES_SHOULD_BE_SUFFIXED = classes().that()
        .areAnnotatedWith(Repository.class)
        .should().haveSimpleNameEndingWith("Repository");

    public static final ArchRule ACTUATOR_ENDPOINT_CLASSES_SHOULD_BE_SUFFIXED = classes().that()
        .areAnnotatedWith(Endpoint.class)
        .should().haveSimpleNameEndingWith("ActuatorEndpoint");

    public static final ArchRule EVENT_HANDLER_CLASSES_SHOULD_BE_SUFFIXED = classes().that()
        .implement("com.kalyp.buildingblocks.backend.communication.event.handler.EventHandler")
        .should().haveSimpleNameEndingWith("EventHandler");
}
