package com.kalyp.buildingblocks.archunit;

import com.kalyp.buildingblocks.archunit.model.packageviolation.ViolationActuatorEndpoint;
import com.kalyp.buildingblocks.archunit.model.packageviolation.ViolationAudit;
import com.kalyp.buildingblocks.archunit.model.packageviolation.ViolationClient;
import com.kalyp.buildingblocks.archunit.model.packageviolation.ViolationConfiguration;
import com.kalyp.buildingblocks.archunit.model.packageviolation.ViolationController;
import com.kalyp.buildingblocks.archunit.model.packageviolation.ViolationMapper;
import com.kalyp.buildingblocks.archunit.model.packageviolation.ViolationRepository;
import com.kalyp.buildingblocks.archunit.model.packageviolation.ViolationService;
import com.kalyp.buildingblocks.archunit.model.repository.PersonRepository;
import com.kalyp.buildingblocks.archunit.model.service.HealthEngineImpl;
import org.junit.jupiter.api.Test;

class ArchitectureRulesTest extends AbstractRulesTest {

    @Test
    void testLayeredArchitectureShouldBeAdopted() {
        testRule(ArchitectureRules.LAYERED_ARCHITECTURE_SHOULD_BE_ADOPTED, PersonRepository.class, 5);
    }

    @Test
    void testControllerClassesShouldBeInAppropriatePackage() {
        testRule(ArchitectureRules.CONTROLLER_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE, ViolationController.class, 1);
    }

    @Test
    void testServiceClassesShouldBeInAppropriatePackage() {
        testRule(ArchitectureRules.SERVICE_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE, ViolationService.class, 1);
    }

    @Test
    void testMapperClassesShouldBeInAppropriatePackage() {
        testRule(ArchitectureRules.MAPPER_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE, ViolationMapper.class, 1);
    }

    @Test
    void testConfigurationClassesShouldBeInAppropriatePackage() {
        testRule(ArchitectureRules.CONFIGURATION_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE,
            ViolationConfiguration.class, 1);
    }

    @Test
    void testRepositoryClassesShouldBeInAppropriatePackage() {
        testRule(ArchitectureRules.REPOSITORY_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE, ViolationRepository.class, 1);
    }

    @Test
    void testClientClassesShouldBeInAppropriatePackage() {
        testRule(ArchitectureRules.CLIENT_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE, ViolationClient.class, 1);
    }

    @Test
    void testActuatorEndpointClassesShouldBeInAppropriatePackage() {
        testRule(ArchitectureRules.ACTUATOR_ENDPOINT_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE,
            ViolationActuatorEndpoint.class, 1);
    }


    @Test
    void testAuditClassesShouldBeInAppropriatePackage() {
        testRule(ArchitectureRules.AUDIT_CLASSES_SHOULD_BE_IN_APPROPRIATE_PACKAGE, ViolationAudit.class, 1);
    }

    @Test
    void testServicesShouldBeSecured() {
        testRule(ArchitectureRules.SERVICES_SHOULD_BE_SECURED, HealthEngineImpl.class, 1);
    }
}
