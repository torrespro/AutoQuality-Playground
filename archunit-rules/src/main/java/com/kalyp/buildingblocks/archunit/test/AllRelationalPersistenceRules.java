package com.kalyp.buildingblocks.archunit.test;

import com.kalyp.buildingblocks.archunit.RelationalPersistenceRules;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/**
 * Class declaring all relational persistence rules as tests for easy adoption in services.
 */
public class AllRelationalPersistenceRules {

    @ArchTest
    ArchRule repositoryCallsShouldBeTransactional = RelationalPersistenceRules.REPOSITORY_CALLS_SHOULD_BE_TRANSACTIONAL;

    @ArchTest
    ArchRule modifyingMethodsShouldHaveParameters = RelationalPersistenceRules.MODIFYING_METHODS_SHOULD_HAVE_PARAMETERS;

    @ArchTest
    ArchRule serviceMethodsShouldBeAnnotatedWithTransactionalCorrectly = RelationalPersistenceRules.SERVICE_METHODS_SHOULD_BE_ANNOTATED_WITH_TRANSACTIONAL_CORRECTLY;

    @ArchTest
    ArchRule noMethodsShouldBeAnnotatedWithJavaxTransactional = RelationalPersistenceRules.NO_METHODS_SHOULD_BE_ANNOTATED_WITH_JAVAX_TRANSACTIONAL;

    @ArchTest
    ArchRule noClassesShouldBeAnnotatedWithJavaxTransactional = RelationalPersistenceRules.NO_CLASSES_SHOULD_BE_ANNOTATED_WITH_JAVAX_TRANSACTIONAL;
}
