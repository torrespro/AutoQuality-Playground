package com.kalyp.buildingblocks.archunit;

import static org.assertj.core.api.Assertions.assertThat;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractRulesTest {

    private static final Logger logger = LoggerFactory.getLogger(AbstractRulesTest.class);

    protected static final JavaClasses classes = new ClassFileImporter()
        .importPackages("com.kalyp.buildingblocks.archunit.model");

    protected void testRule(ArchRule rule, Class<?> violator, int count) {
        try {
            rule.check(classes);
            Assertions.fail("At least one violation was expected");
        } catch (AssertionError e) {
            assertViolation(rule, violator, count, e);
        }
    }

    protected void assertViolation(ArchRule rule, Class<?> violator, int count, AssertionError e) {
        try {
            assertThat(e).hasMessageContaining(rule.toString());
            assertThat(e).hasMessageContaining(violator.getName());
            assertThat(e).hasMessageContaining("was violated (%s times):".formatted(count));
        } catch (AssertionError failure) {
            logger.error("Expected violation wasn't found", e);
            throw failure;
        }
    }
}
