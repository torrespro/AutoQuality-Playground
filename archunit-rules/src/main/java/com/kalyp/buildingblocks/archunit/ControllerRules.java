package com.kalyp.buildingblocks.archunit;

import static com.tngtech.archunit.lang.SimpleConditionEvent.violated;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import java.lang.annotation.Annotation;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rules related to REST controllers.
 */
public class ControllerRules {

    private static final Class<DateTimeFormat> DATE_TIME_FORMAT_ANNOTATION = DateTimeFormat.class;
    private static final List<Class<? extends Temporal>> JAVA_TIME_TYPES = List
        .of(LocalDate.class, LocalTime.class, LocalDateTime.class, OffsetDateTime.class, OffsetTime.class,
            ZonedDateTime.class, Instant.class);

    private ControllerRules() {
    }

    private static final ArchCondition<JavaMethod> HAVE_DATE_AND_TIME_PARAMETERS_ANNOTATED = new ArchCondition<>(
        "have date/time parameters annotated with @" + DATE_TIME_FORMAT_ANNOTATION.getSimpleName()) {
        @Override
        public void check(JavaMethod item, ConditionEvents events) {
            List<JavaClass> parameterTypes = item.getRawParameterTypes();
            Annotation[][] annotations = null;
            boolean allJavaTimeParametersAnnotated = true;
            for (int i = 0; i < parameterTypes.size(); i++) {
                if (isJavaTimeType(parameterTypes.get(i))) {
                    if (annotations == null) {
                        annotations = item.reflect().getParameterAnnotations();
                    }
                    allJavaTimeParametersAnnotated = Arrays.stream(annotations[i])
                        .anyMatch(annotation -> annotation.annotationType().equals(DATE_TIME_FORMAT_ANNOTATION));
                    if (!allJavaTimeParametersAnnotated) {
                        break;
                    }
                }
            }
            if (!allJavaTimeParametersAnnotated) {
                events.add(violated(item,
                    item + " contains date/time parameters missing @" + DATE_TIME_FORMAT_ANNOTATION.getSimpleName()));
            }
        }

        private boolean isJavaTimeType(JavaClass javaClass) {
            return JAVA_TIME_TYPES.stream().anyMatch(javaClass::isAssignableTo);
        }
    };

    public static final ArchRule DATE_AND_TIME_CONTROLLER_PARAMETERS_SHOULD_BE_ANNOTATED = methods().that()
        .areDeclaredInClassesThat().areAnnotatedWith(RestController.class)
        .and().arePublic()
        .or().arePackagePrivate()
        .should(HAVE_DATE_AND_TIME_PARAMETERS_ANNOTATED)
        .because("SpringMVC needs this annotation to parse date/times correctly");
}
