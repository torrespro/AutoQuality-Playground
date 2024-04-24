package com.kalyp.buildingblocks.archunit;

import com.kalyp.buildingblocks.archunit.model.repository.PersonRepository;
import com.kalyp.buildingblocks.archunit.model.service.HealthCareService;
import com.kalyp.buildingblocks.archunit.model.service.HealthEngineImpl;
import com.kalyp.buildingblocks.archunit.model.service.HealthMonitoringService;
import com.kalyp.buildingblocks.archunit.model.service.LegacyHealthCareService;
import com.kalyp.buildingblocks.archunit.model.service.LifeServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class RelationalPersistenceRulesTest extends AbstractRulesTest {

    @Test
    void testRepositoryCallsShouldBeTransactional() {
        testRule(RelationalPersistenceRules.REPOSITORY_CALLS_SHOULD_BE_TRANSACTIONAL, HealthEngineImpl.class, 3);
    }

    @Test
    void testModifyingMethodsShouldHaveParameters() {
        testRule(RelationalPersistenceRules.MODIFYING_METHODS_SHOULD_HAVE_PARAMETERS, PersonRepository.class, 1);
    }

    @Test
    void testServiceMethodsShouldBeAnnotatedWithTransactionalCorrectly() {
        testRule(RelationalPersistenceRules.SERVICE_METHODS_SHOULD_BE_ANNOTATED_WITH_TRANSACTIONAL_CORRECTLY, HealthCareService.class, 3);
        testRule(RelationalPersistenceRules.SERVICE_METHODS_SHOULD_BE_ANNOTATED_WITH_TRANSACTIONAL_CORRECTLY, HealthMonitoringService.class, 3);
        testRule(RelationalPersistenceRules.SERVICE_METHODS_SHOULD_BE_ANNOTATED_WITH_TRANSACTIONAL_CORRECTLY, LifeServiceImpl.class, 3);
    }

    @Test
    void testNoMethodsShouldBeAnnotatedWithJavaxTransactional() {
        testRule(RelationalPersistenceRules.NO_METHODS_SHOULD_BE_ANNOTATED_WITH_JAVAX_TRANSACTIONAL, LegacyHealthCareService.class, 1);
    }

    @Test
    void testNoClassesShouldBeAnnotatedWithJavaxTransactional() {
        testRule(RelationalPersistenceRules.NO_CLASSES_SHOULD_BE_ANNOTATED_WITH_JAVAX_TRANSACTIONAL, LegacyHealthCareService.class, 1);
    }
}
