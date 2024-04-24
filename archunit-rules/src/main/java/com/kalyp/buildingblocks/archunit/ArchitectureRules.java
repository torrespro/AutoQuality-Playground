package com.kalyp.buildingblocks.archunit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.lang.ArchRule;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rules related to overarching architectural patterns.
 */
public class ArchitectureRules {

    private static final String CONTROLLER_LAYER = "Controller";
    private static final String SERVICE_LAYER = "Service";
    private static final String MAPPING_LAYER = "Mapping";
    private static final String AUDIT_LAYER = "Audit";
    private static final String CONFIGURATION_LAYER = "Configuration";
    private static final String REPOSITORY_LAYER = "Repository";
    private static final String CLIENT_LAYER = "Client";
    private static final String ACTUATOR_LAYER = "Actuator";
    private static final String EVENT_LISTENER_LAYER = "EventListener";

    private static final String API_PACKAGE = "..api..";
    private static final String REST_PACKAGE = "..rest..";
    private static final String CONTROLLER_PACKAGE = "..controller..";

    private static final String SERVICE_PACKAGE = "..service..";
    private static final String MAPPER_PACKAGE = "..mapper..";
    private static final String AUDIT_PACKAGE = "..audit..";
    private static final String CONFIGURATION_PACKAGE = "..configuration..";
    private static final String CONFIG_PACKAGE = "..config..";
    private static final String REPOSITORY_PACKAGE = "..repository..";
    private static final String PERSISTENCE_PACKAGE = "..persistence..";
    private static final String CLIENT_PACKAGE = "..client..";
    private static final String ACTUATOR_PACKAGE = "..actuator..";
    private static final String LISTENER_PACKAGE = "..listener..";

    private ArchitectureRules() {
    }

    public static final ArchRule LAYERED_ARCHITECTURE_SHOULD_BE_ADOPTED = layeredArchitecture()
            .consideringAllDependencies()
        .layer(CONTROLLER_LAYER).definedBy(API_PACKAGE, REST_PACKAGE, CONTROLLER_PACKAGE)
        .layer(SERVICE_LAYER).definedBy(SERVICE_PACKAGE)
        .layer(MAPPING_LAYER).definedBy(MAPPER_PACKAGE)
        .layer(AUDIT_LAYER).definedBy(AUDIT_PACKAGE)
        .layer(CONFIGURATION_LAYER).definedBy(CONFIGURATION_PACKAGE, CONFIG_PACKAGE)
        .layer(REPOSITORY_LAYER).definedBy(REPOSITORY_PACKAGE, PERSISTENCE_PACKAGE)
        .layer(CLIENT_LAYER).definedBy(CLIENT_PACKAGE)
        .layer(ACTUATOR_LAYER).definedBy(ACTUATOR_PACKAGE)
        .layer(EVENT_LISTENER_LAYER).definedBy(LISTENER_PACKAGE)
        .withOptionalLayers(true)
        .whereLayer(CONTROLLER_LAYER).mayNotBeAccessedByAnyLayer()
        .whereLayer(ACTUATOR_LAYER).mayNotBeAccessedByAnyLayer()
        .whereLayer(EVENT_LISTENER_LAYER).mayNotBeAccessedByAnyLayer()
        .whereLayer(MAPPING_LAYER)
        .mayOnlyBeAccessedByLayers(CONTROLLER_LAYER, CONFIGURATION_LAYER, SERVICE_LAYER, CLIENT_LAYER,
            EVENT_LISTENER_LAYER)
        .whereLayer(SERVICE_LAYER)
        .mayOnlyBeAccessedByLayers(CONTROLLER_LAYER, ACTUATOR_LAYER, EVENT_LISTENER_LAYER, AUDIT_LAYER)
        .whereLayer(CONFIGURATION_LAYER).mayOnlyBeAccessedByLayers(CONTROLLER_LAYER, SERVICE_LAYER, CLIENT_LAYER)
        .whereLayer(REPOSITORY_LAYER).mayOnlyBeAccessedByLayers(SERVICE_LAYER, MAPPING_LAYER);

    public static final ArchRule CONTROLLER_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE = classes().that()
        .areAnnotatedWith(RestController.class)
        .or().haveNameMatching(".*Controller*")
        .should().resideInAPackage(API_PACKAGE)
        .orShould().resideInAPackage(REST_PACKAGE)
        .orShould().resideInAPackage(CONTROLLER_PACKAGE);

    public static final ArchRule SERVICE_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE = classes().that()
        .areAnnotatedWith(Service.class)
        .or().haveNameMatching(".*Service*")
        .should().resideInAPackage(SERVICE_PACKAGE);

    public static final ArchRule MAPPER_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE = classes().that()
        .areAnnotatedWith("org.mapstruct.Mapper")
        .or().haveNameMatching(".*Mapper*")
        .should().resideInAPackage(MAPPER_PACKAGE);

    public static final ArchRule CONFIGURATION_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE = classes().that()
        .areAnnotatedWith(Configuration.class)
        .or().haveNameMatching(".*Configuration*")
        .should().resideInAPackage(CONFIGURATION_PACKAGE)
        .orShould().resideInAPackage(CONFIG_PACKAGE);

    public static final ArchRule REPOSITORY_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE = classes().that()
        .areAnnotatedWith(Repository.class)
        .or().haveNameMatching(".*Repository*")
        .should().resideInAPackage(REPOSITORY_PACKAGE)
        .orShould().resideInAPackage(PERSISTENCE_PACKAGE);

    public static final ArchRule CLIENT_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE = classes().that()
        .haveNameMatching(".*Client")
        .should().resideInAPackage(CLIENT_PACKAGE);

    public static final ArchRule ACTUATOR_ENDPOINT_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE = classes().that()
        .areAnnotatedWith(Endpoint.class)
        .or().haveNameMatching(".*ActuatorEndpoint*")
        .should().resideInAPackage(ACTUATOR_PACKAGE);

    public static final ArchRule AUDIT_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE = classes().that()
        .haveNameMatching(".*Audit*")
        .should().resideInAPackage(AUDIT_PACKAGE);

    public static final ArchRule EVENT_HANDLER_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE = classes().that()
        .implement("com.kalyp.buildingblocks.backend.communication.event.handler.EventHandler")
        .should().resideInAPackage(LISTENER_PACKAGE);

    public static final ArchRule SERVICES_SHOULD_BE_SECURED = methods().that()
        .areDeclaredInClassesThat().areAnnotatedWith(Service.class)
        .and().arePublic()
        .should().beAnnotatedWith(PreAuthorize.class)
        .orShould().beAnnotatedWith(PostConstruct.class)
        .because("the business layer is supposed to be secure");
}
