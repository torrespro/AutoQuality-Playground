package com.kalyp.buildingblocks.archunit.condition;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.kalyp.buildingblocks.archunit.AbstractRulesTest;
import com.kalyp.buildingblocks.archunit.model.persistence.LifeStore;
import com.kalyp.buildingblocks.archunit.model.service.HealthCareService;
import com.kalyp.buildingblocks.archunit.model.service.LifeServiceImpl;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import java.util.Set;
import org.junit.jupiter.api.Test;

class AppropriateRepositoryAccessConditionTest extends AbstractRulesTest {

    private final AppropriateRepositoryAccessCondition condition = new AppropriateRepositoryAccessCondition();

    @Test
    void testIsReadOnlyMethod() {
        // method annotation with @Modifying
        JavaMethod method = classes.get(LifeStore.class).getMethod("writeHeartRate", int.class);
        assertFalse(condition.isReadOnlyMethod(method));

        // method annotated with @Query
        method = classes.get(LifeStore.class).getMethod("fetchByHeartRateHigherThan", int.class);
        assertTrue(condition.isReadOnlyMethod(method));

        Set<String> methodsByPrefix = Set.of("count", "existsById", "findAll", "getOne", "queryByHeartRate",
            "readByHeartRate", "searchByHeartRate", "streamByHeartRate");
        methodsByPrefix.stream()
            .map(methodName -> getMethodByName(classes.get(LifeStore.class), methodName))
            .forEach(methodByName -> assertTrue(condition.isReadOnlyMethod(methodByName)));

        // method name starting with "save"
        method = getMethodByName(classes.get(LifeStore.class), "saveAll");
        assertFalse(condition.isReadOnlyMethod(method));
    }

    @Test
    void testIsReadOnlyTransactional() {
        // method without annotation falling back to class level annotation
        JavaMethod method = classes.get(LifeServiceImpl.class).getMethod("beBorn");
        assertTrue(condition.isReadOnlyTransactional(method));

        // method with readOnly annotation
        method = classes.get(HealthCareService.class).getMethod("validateUsage");
        assertTrue(condition.isReadOnlyTransactional(method));

        // method without annotation falling back to class level annotation without readOnly
        method = classes.get(HealthCareService.class).getMethod("closeContract");
        assertFalse(condition.isReadOnlyTransactional(method));
    }

    private JavaMethod getMethodByName(JavaClass javaClass, String name) {
        return javaClass.getAllMethods().stream()
            .filter(m -> m.getName().equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(name + " method not found"));
    }
}
