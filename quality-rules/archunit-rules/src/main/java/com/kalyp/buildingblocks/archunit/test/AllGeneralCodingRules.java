package com.kalyp.buildingblocks.archunit.test;

import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JODATIME;

import com.kalyp.buildingblocks.archunit.KalypGeneralCodingRules;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/**
 * Class declaring all Kalyp general coding rules as tests for easy adoption in services.
 */
public class AllGeneralCodingRules {

    @ArchTest
    ArchRule noClassesShouldAccessStandardStreams = NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

    @ArchTest
    ArchRule noClassesShouldThrowGenericExceptions = NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

    @ArchTest
    ArchRule noClassesShouldUseJavaUtilLogging = NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

    @ArchTest
    ArchRule noClassesShouldUseJodatime = NO_CLASSES_SHOULD_USE_JODATIME;

    @ArchTest
    ArchRule noClassesShouldUseFieldInjection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

    @ArchTest
    ArchRule noJavaUtilDate = KalypGeneralCodingRules.NO_JAVA_UTIL_DATE;

    @ArchTest
    ArchRule noJavaUtilCalendar = KalypGeneralCodingRules.NO_JAVA_UTIL_CALENDAR;

    @ArchTest
    ArchRule noSimpleDateFormat = KalypGeneralCodingRules.NO_SIMPLE_DATE_FORMAT;
}
