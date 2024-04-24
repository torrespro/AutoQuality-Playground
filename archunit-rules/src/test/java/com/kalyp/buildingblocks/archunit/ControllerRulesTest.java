package com.kalyp.buildingblocks.archunit;

import com.kalyp.buildingblocks.archunit.model.rest.GameOfLifeController;
import org.junit.jupiter.api.Test;

class ControllerRulesTest extends AbstractRulesTest {

    @Test
    void testDateAndTimeControllerParametersShouldBeAnnotated() {
        testRule(ControllerRules.DATE_AND_TIME_CONTROLLER_PARAMETERS_SHOULD_BE_ANNOTATED, GameOfLifeController.class,
            1);
    }
}
