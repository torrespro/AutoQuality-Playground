# Archunit

Utilities for using ArchUnit and Kalyp-specific rules.

## How to use

Create unit test classes that reference rules provided in this module like the following example:

```java
import com.archunit.ConfigurationRules;
import com.my.service.Application;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packagesOf = Application.class, importOptions = ImportOption.DoNotIncludeTests.class)
public class ConfigurationRulesTest {

    @ArchTest
    ArchRule configurationClassesShouldBeUnderKalypKey = ConfigurationRules.CONFIGURATION_CLASSES_SHOULD_BE_UNDER_KALYP_KEY;

    @ArchTest
    ArchRule configurationClassesShouldBeValidated = ConfigurationRules.CONFIGURATION_CLASSES_SHOULD_BE_VALIDATED;

    @ArchTest
    ArchRule configurationClassesShouldBeContextScoped = ConfigurationRules.CONFIGURATION_CLASSES_SHOULD_BE_CONTEXT_SCOPED;
}
```

It's possible to import all rules related to a topic with a one-liner, one advantage with this approach is that new
rules will be adopted automatically with every SSDK upgrade:

```java
@AnalyzeClasses(packagesOf = Application.class, importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchUnitRulesTest {

    @ArchTest
    ArchTests architectureRules = ArchTests.in(AllArchitectureRules.class);
}
```
