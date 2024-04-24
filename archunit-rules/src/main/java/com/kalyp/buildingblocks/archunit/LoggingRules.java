package com.kalyp.buildingblocks.archunit;

import static com.tngtech.archunit.lang.SimpleConditionEvent.violated;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Rules related to logging best practices.
 */
public class LoggingRules {

    private LoggingRules() {
    }

    public static final ArchRule LOGGERS_SHOULD_BE_PRIVATE_STATIC_FINAL = fields().that()
        .haveRawType(Logger.class)
        .should().bePrivate()
        .andShould().beStatic()
        .andShould().beFinal()
        .because("it's a best practice agreed upon by the Backend Guild");

    private static final ArchCondition<JavaMethod> HAVE_LOGGING_CALLS_CONDITION = new ArchCondition<>(
        "have SLF4J logging calls") {
        @Override
        public void check(JavaMethod item, ConditionEvents events) {
            boolean callsLogger = item.getCallsFromSelf().stream()
                .anyMatch(call -> call.getTarget().getOwner().isAssignableFrom(Logger.class));
            if (!callsLogger) {
                events.add(violated(item, item.getDescription() + " doesn't call SLF4J logging methods"));
            }
        }
    };

    public static final ArchRule SERVICES_SHOULD_HAVE_LOGGING = methods().that()
        .areDeclaredInClassesThat().areAnnotatedWith(Service.class)
        .and().arePublic()
        .should(HAVE_LOGGING_CALLS_CONDITION)
        .because("lack of logging makes it hard to debug issues");

    private static final ArchCondition<JavaMethod> LOG_PARAMETERIZED_MESSAGES_CONDITION = new ArchCondition<>(
        "log parameterized messages") {
        @Override
        public void check(JavaMethod item, ConditionEvents events) {
            boolean hasMessageOnlyCalls = item.getCallsFromSelf().stream()
                .filter(call -> call.getTarget().getOwner().isAssignableFrom(Logger.class))
                .map(call -> call.getTarget().getRawParameterTypes())
                .filter(parameterTypes -> parameterTypes.size() == 1)
                .anyMatch(parameterTypes -> parameterTypes.getFirst().isAssignableFrom(String.class));
            if (hasMessageOnlyCalls) {
                events.add(violated(item, item.getDescription() + " logs only a message without parameters"));
            }
        }
    };

    public static final ArchRule LOGGING_MESSAGES_SHOULD_BE_PARAMETERIZED = methods()
        .should(LOG_PARAMETERIZED_MESSAGES_CONDITION)
        .because("log messages without context aren't very useful for debugging");
}
