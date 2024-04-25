package es.torres.books.archunit;

import com.kalyp.buildingblocks.archunit.test.*;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchTests;
import es.torres.books.BookApplication;
import org.junit.jupiter.api.Disabled;

@ArchIgnore
@AnalyzeClasses(packagesOf = BookApplication.class, importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchUnitRulesTest {

    @ArchTest
    ArchTests architectureRules = ArchTests.in(AllArchitectureRules.class);

    @ArchTest
    ArchTests configurationRules = ArchTests.in(AllConfigurationRules.class);

    @ArchTest
    ArchTests controllerRules = ArchTests.in(AllControllerRules.class);

    @ArchTest
    ArchTests dataMappingRules = ArchTests.in(AllDataMappingRules.class);

    @ArchTest
    ArchTests generalCodingRules = ArchTests.in(AllGeneralCodingRules.class);

    @ArchTest
    ArchTests loggingRules = ArchTests.in(AllLoggingRules.class);

    @ArchTest
    ArchTests namingConventionRules = ArchTests.in(AllNamingConventionRules.class);

    @ArchTest
    ArchTests relationalPersistenceRules = ArchTests.in(AllRelationalPersistenceRules.class);
}