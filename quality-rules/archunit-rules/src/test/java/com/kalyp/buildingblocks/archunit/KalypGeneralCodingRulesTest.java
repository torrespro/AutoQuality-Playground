package com.kalyp.buildingblocks.archunit;

import com.kalyp.buildingblocks.archunit.model.rest.GameOfLifeController;
import com.kalyp.buildingblocks.archunit.model.service.LifeServiceImpl;
import org.junit.jupiter.api.Test;

class KalypGeneralCodingRulesTest extends AbstractRulesTest {

    @Test
    void testNoJavaUtilDate() {
        testRule(KalypGeneralCodingRules.NO_JAVA_UTIL_DATE, LifeServiceImpl.class, 1);
    }

    @Test
    void testNoJavaUtilCalendar() {
        testRule(KalypGeneralCodingRules.NO_JAVA_UTIL_CALENDAR, LifeServiceImpl.class, 1);
    }

    @Test
    void testNoSimpleDateFormat() {
        testRule(KalypGeneralCodingRules.NO_SIMPLE_DATE_FORMAT, GameOfLifeController.class, 2);
    }
}
