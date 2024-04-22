package com.kalyp.buildingblocks.archunit;

import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Rules related to general coding conventions and best practices.
 */
public class KalypGeneralCodingRules {

    private KalypGeneralCodingRules() {
    }

    public static final ArchRule NO_JAVA_UTIL_DATE = noClasses()
        .should().dependOnClassesThat().areAssignableTo(Date.class)
        .because("package java.time provides better types to work with date and time");

    public static final ArchRule NO_JAVA_UTIL_CALENDAR = noClasses()
        .should().dependOnClassesThat().areAssignableTo(Calendar.class)
        .because("package java.time provides better types to work with date and time");

    public static final ArchRule NO_SIMPLE_DATE_FORMAT = noClasses()
        .should().dependOnClassesThat().areAssignableTo(SimpleDateFormat.class)
        .because("package java.time provides better types to work with date and time formatting");
}
