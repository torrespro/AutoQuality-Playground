package com.kalyp.buildingblocks.archunit;

import static com.tngtech.archunit.lang.SimpleConditionEvent.violated;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noMethods;

import com.kalyp.buildingblocks.archunit.condition.AppropriateRepositoryAccessCondition;
import com.kalyp.buildingblocks.archunit.predicate.CallRepositoryPredicate;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Rules related to relational database persistence best practices.
 */
public class RelationalPersistenceRules {

    private RelationalPersistenceRules() {
    }

    public static final ArchRule REPOSITORY_CALLS_SHOULD_BE_TRANSACTIONAL = classes().that()
        .areAnnotatedWith(Repository.class)
        .should().onlyHaveDependentClassesThat().areAnnotatedWith(Transactional.class)
        .because("database access needs well-defined transactional semantics");

    private static final ArchCondition<JavaMethod> HAVE_AT_LEAST_ONE_PARAMETER = new ArchCondition<>(
        "have at least one parameter") {
        @Override
        public void check(JavaMethod item, ConditionEvents events) {
            if (item.getRawParameterTypes().isEmpty()) {
                events.add(violated(item, item + " has no parameters"));
            }
        }
    };

    public static final ArchRule MODIFYING_METHODS_SHOULD_HAVE_PARAMETERS = methods().that()
        .areDeclaredInClassesThat().areAnnotatedWith(Repository.class)
        .and().areAnnotatedWith(Modifying.class)
        .should(HAVE_AT_LEAST_ONE_PARAMETER)
        .because("updates and deletes without parameters might affect all database rows");

    public static final ArchRule SERVICE_METHODS_SHOULD_BE_ANNOTATED_WITH_TRANSACTIONAL_CORRECTLY = methods().that()
        .areDeclaredInClassesThat().areAnnotatedWith(Service.class)
        .and().arePublic()
        .and(new CallRepositoryPredicate())
        .should(new AppropriateRepositoryAccessCondition())
        .because("read-only transactions can be executed against read replicas improving overall system performance");

    public static final ArchRule NO_METHODS_SHOULD_BE_ANNOTATED_WITH_JAVAX_TRANSACTIONAL = noMethods().should()
        .beAnnotatedWith("jakarta.transaction.Transactional")
        .because("we consistently want to use Transactional annotation provided by Spring");

    public static final ArchRule NO_CLASSES_SHOULD_BE_ANNOTATED_WITH_JAVAX_TRANSACTIONAL = noClasses().should()
        .beAnnotatedWith("jakarta.transaction.Transactional")
        .because("we consistently want to use Transactional annotation provided by Spring");
}
