package com.kalyp.buildingblocks.archunit.condition;

import static com.tngtech.archunit.lang.SimpleConditionEvent.violated;

import com.kalyp.buildingblocks.archunit.util.JavaMethods;
import com.google.common.annotations.VisibleForTesting;
import java.util.Optional;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaMethodCall;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Checks whether a method is marked readOnly or not depending on the repository methods it calls.
 */
public class AppropriateRepositoryAccessCondition extends ArchCondition<JavaMethod> {

    private static final List<String> READ_ONLY_PREFIXES = List.of("count", "exists", "find", "get", "query", "read",
        "search", "stream");

    public AppropriateRepositoryAccessCondition() {
        super("be annotated correctly with @Transactional");
    }

    @Override
    public void check(JavaMethod item, ConditionEvents events) {
        Set<JavaMethodCall> calls = new HashSet<>();
        JavaMethods.getMethodCallsFromSelf(item, calls);

        boolean readOnlyCalls = calls.stream()
            .filter(call -> call.getTargetOwner().isAssignableTo(Repository.class))
            .map(call -> call.getTarget().resolveMember())
            .map(method -> method.get())
            .allMatch(this::isReadOnlyMethod);

//        boolean readOnlyCalls = calls.stream()
//                .filter(call -> call.getTargetOwner().isAssignableTo(Repository.class))
//                .map(call -> call.getTarget().resolve())
//                .filter(resolvedMethods -> resolvedMethods.size() == 1)
//                .map(resolvedMethods -> resolvedMethods.iterator().next())
//                .allMatch(this::isReadOnlyMethod);


        boolean readOnlyTransactional = isReadOnlyTransactional(item);

        if (readOnlyCalls && !readOnlyTransactional) {
            events.add(
                violated(item, item.getDescription() + " only reads data but is not marked with readOnly=true"));
        } else if (!readOnlyCalls && readOnlyTransactional) {
            events.add(
                violated(item, item.getDescription() + " writes data but is marked with readOnly=true"));
        }
    }

    @VisibleForTesting
    boolean isReadOnlyMethod(JavaMethod method) {
        return !method.isAnnotatedWith(Modifying.class) && (READ_ONLY_PREFIXES.stream()
            .anyMatch(prefix -> method.getName().startsWith(prefix))
            || method.isAnnotatedWith(Query.class));
    }

    @VisibleForTesting
    boolean isReadOnlyTransactional(JavaMethod method) {
        Optional<Transactional> transactional = method.tryGetAnnotationOfType(Transactional.class);
        if (transactional.isEmpty()) {
            transactional = method.getOwner().tryGetAnnotationOfType(Transactional.class);
        }
        return transactional.map(Transactional::readOnly).orElse(false);
    }
}
