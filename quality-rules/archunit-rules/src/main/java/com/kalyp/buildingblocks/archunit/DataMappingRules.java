package com.kalyp.buildingblocks.archunit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAnnotation;
import com.tngtech.archunit.core.domain.JavaEnumConstant;
import com.tngtech.archunit.lang.ArchRule;

/**
 * Rules related to MapStruct data mapping.
 *
 * <p>This rule ensures that {@code unmappedTargetPolicy} is set to {@link org.mapstruct.ReportingPolicy#ERROR}.
 *
 * <p>The following code snippet shows you not compliant code:
 * <pre>{@code
 *  @Mapper
 *  public interface PojoMapper {
 *      // omitted code
 *  }
 * }</pre>
 *
 * <p>To make the aforementioned example compliant you have to add the following configuration:
 * <pre>{@code
 *  @Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
 *  public interface PojoMapper {
 *      // omitted code
 *  }
 * }</pre>
 *
 * <p>In case your <code>source</code> and <code>target</code> objects have different number of fields, or fields have
 * different names - you have to configure mapping rules explicitly. Let's review the following example:
 * <pre>{@code
 *  @Mapper
 *  public interface Source {
 *      String field1;
 *      String field2WithDifferentName;
 *  }
 *
 *  public class Target {
 *      String field1;
 *      String field2;
 *      String field3;
 *  }
 * }</pre>
 *
 * <p>The in order to make your mapping complient with this rule you have to provide the following configuration for
 * the
 * Mapper.
 * <pre>{@code
 *  @Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
 *  public interface PojoMapper {
 *
 *       // same name fields are mapped automatically, so this isn't really required
 *      @Mapping(target = "field1", source = "field1")
 *      // fields with different names must be mapped explicitly
 *      @Mapping(target = "field2", source = "field2WithDifferentName")
 *      // this one must be here otherwise MapStruct will flag "field3" as not being mapped
 *      @Mapping(target = "field3", ignored = true)
 *      Target mapFromSource(Source source);
 *  }
 * }</pre>
 */
public class DataMappingRules {

    private static final String UNMAPPED_TARGET_POLICY = "unmappedTargetPolicy";
    private static final String ERROR_POLICY = "ERROR";

    private DataMappingRules() {
    }

    private static final DescribedPredicate<JavaAnnotation<?>> ERROR_UNMAPPED_TARGET_POLICY = new DescribedPredicate<>(
        UNMAPPED_TARGET_POLICY + "=" + ERROR_POLICY) {
        @Override
        @SuppressWarnings("unchecked") // ArchUnit's Optional doesn't handle generic types well
        public boolean test(JavaAnnotation input) {
            return (boolean) input.get(UNMAPPED_TARGET_POLICY)
                .map(policyValue -> ((JavaEnumConstant) policyValue).name())
                .map(ERROR_POLICY::equals)
                .orElse(false);
        }
    };

    public static final ArchRule MAPPERS_SHOULD_GENERATE_ERROR_ON_UNMAPPED_TARGET = classes().that()
        .areAnnotatedWith("org.mapstruct.Mapper")
        .should()
        .beAnnotatedWith(ERROR_UNMAPPED_TARGET_POLICY)
        .because("attributes can be missed, even more so when the target class changes after creation");
}
