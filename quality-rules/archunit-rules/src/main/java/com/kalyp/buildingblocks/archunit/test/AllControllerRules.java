package com.kalyp.buildingblocks.archunit.test;

import com.kalyp.buildingblocks.archunit.ControllerRules;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/**
 * Class declaring all controller rules as tests for easy adoption in services.
 */
public class AllControllerRules {

    @ArchTest
    ArchRule dateAndTimeControllerParametersShouldBeAnnotated =
        ControllerRules.DATE_AND_TIME_CONTROLLER_PARAMETERS_SHOULD_BE_ANNOTATED;
}
