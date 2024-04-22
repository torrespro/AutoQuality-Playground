package com.kalyp.buildingblocks.archunit.option;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tngtech.archunit.core.importer.Location;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class DoNotIncludeMapperImplementationsTest {

    private final DoNotIncludeMapperImplementations option = new DoNotIncludeMapperImplementations();

    @Test
    void testIncludesTestClass() {
        Location testClass = Location.of(Path.of(getClass().getName() + ".class"));
        assertTrue(option.includes(testClass));
    }

    @Test
    void testIncludesMapperClass() {
        Location mapperClass = Location.of(Path.of("PersonMapperImpl.class"));
        assertFalse(option.includes(mapperClass));
    }
}
