package com.kalyp.buildingblocks.archunit;

import static com.tngtech.archunit.lang.SimpleConditionEvent.violated;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

/**
 * Rules related to service configuration.
 */
public class ConfigurationRules {

    private static final String KALYP_CONFIG_KEY = "kalyp.";

    private ConfigurationRules() {
    }

    private static final ArchCondition<JavaClass> KALYP_KEY_ANNOTATION_CONDITION = new ArchCondition<>(
        "be under kalyp key") {
        @Override
        public void check(JavaClass input, ConditionEvents events) {
            ConfigurationProperties configurationProperties = input.getAnnotationOfType(ConfigurationProperties.class);
            String value = configurationProperties.value();
            if (value.isBlank()) {
                value = configurationProperties.prefix();
            }
            if (!value.startsWith(KALYP_CONFIG_KEY)) {
                String msg = "@%s annotation on %s should have value starting with '%s'".formatted(
                    ConfigurationProperties.class.getSimpleName(), input.getDescription(), KALYP_CONFIG_KEY);
                events.add(violated(input, msg));
            }
        }
    };

    public static final ArchRule CONFIGURATION_CLASSES_SHOULD_BE_UNDER_KALYP_KEY = classes().that()
        .areAnnotatedWith(Configuration.class)
        .and().areAnnotatedWith(ConfigurationProperties.class)
        .should(KALYP_KEY_ANNOTATION_CONDITION)
        .because("it's Kalyp specific configuration");

    public static final ArchRule CONFIGURATION_CLASSES_SHOULD_BE_VALIDATED = classes().that()
        .areAnnotatedWith(Configuration.class)
        .should().beAnnotatedWith(Validated.class)
        .because("services need to consume only valid values");

    public static final ArchRule CONFIGURATION_CLASSES_SHOULD_BE_CONTEXT_SCOPED = classes().that()
        .areAnnotatedWith(Configuration.class)
        .should().beAnnotatedWith("com.kalyp.buildingblocks.context.ContextScoped")
        .because("multi-tenancy support requires context scoped beans");

    public static final ArchRule API_CLIENT_BEANS_SHOULD_BE_CONTEXT_SCOPED = methods().that()
        .areDeclaredInClassesThat().areAnnotatedWith(Configuration.class)
        .and().areDeclaredInClassesThat()
        .areAssignableTo("com.kalyp.buildingblocks.communication.client.ApiClientConfig")
        .and().areAnnotatedWith(Bean.class)
        .should().beAnnotatedWith("com.kalyp.buildingblocks.context.ContextScoped")
        .because("multi-tenancy support for service to service calls requires context scoped beans");
}
