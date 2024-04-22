package com.kalyp.buildingblocks.archunit.test;

import com.kalyp.buildingblocks.archunit.DataMappingRules;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/**
 * Class declaring all data mapping rules as tests for easy adoption in services.
 */
public class AllDataMappingRules {

    @ArchTest
    ArchRule mappersShouldGenerateErrorOnUnmappedTarget =
        DataMappingRules.MAPPERS_SHOULD_GENERATE_ERROR_ON_UNMAPPED_TARGET;
}
