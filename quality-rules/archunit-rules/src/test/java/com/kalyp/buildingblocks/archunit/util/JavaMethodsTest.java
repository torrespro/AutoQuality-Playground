package com.kalyp.buildingblocks.archunit.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.kalyp.buildingblocks.archunit.AbstractRulesTest;
import com.kalyp.buildingblocks.archunit.model.service.HealthCareService;
import com.kalyp.buildingblocks.archunit.model.service.HealthMonitoringService;
import com.kalyp.buildingblocks.archunit.model.service.LifeServiceImpl;
import com.tngtech.archunit.core.domain.AccessTarget.MethodCallTarget;
import com.tngtech.archunit.core.domain.JavaAccess;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaMethodCall;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class JavaMethodsTest extends AbstractRulesTest {

    @Test
    void testGetPolymorphicCallsIfAny() {
        JavaMethod method = classes.get(LifeServiceImpl.class).getMethod("retire");
        Set<JavaMethodCall> calls = new HashSet<>();
        JavaMethods.getMethodCallsFromSelf(method, calls);
        assertEquals(Set.of("getLotsOfMedicine", "findAll", "count", "now", "info"),getCalledMethodNames(calls));
    }

    @Test
    void testGetMethodCallsFromSelfToRepositoryDirectly() {
        JavaMethod method = classes.get(HealthCareService.class).getMethod("closeContract");
        Set<JavaMethodCall> calls = new HashSet<>();
        JavaMethods.getMethodCallsFromSelf(method, calls);
        assertEquals(Set.of("count", "now", "info"), getCalledMethodNames(calls));
    }

    @Test
    void testGetMethodCallsFromSelfToAnotherService() {
        JavaMethod method = classes.get(HealthMonitoringService.class).getMethod("customerPassedAway");
        Set<JavaMethodCall> calls = new HashSet<>();
        JavaMethods.getMethodCallsFromSelf(method, calls);
        assertEquals(Set.of("closeContract", "count", "now", "info"), getCalledMethodNames(calls));
    }

    private Set<String> getCalledMethodNames(Set<JavaMethodCall> calls) {
        return calls.stream()
            .map(JavaAccess::getTarget)
            .map(MethodCallTarget::resolveMember)
            .map(method -> method.get())
            .map(JavaMethod::getName)
            .collect(Collectors.toSet());
    }
}
