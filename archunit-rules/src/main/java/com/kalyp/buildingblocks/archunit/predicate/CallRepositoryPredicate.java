package com.kalyp.buildingblocks.archunit.predicate;

import com.kalyp.buildingblocks.archunit.util.JavaMethods;
import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaMethodCall;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.repository.Repository;

public class CallRepositoryPredicate extends DescribedPredicate<JavaMethod> {

    public CallRepositoryPredicate() {
        super("call Repository methods");
    }

    @Override
    public boolean test(JavaMethod input) {
        Set<JavaMethodCall> calls = new HashSet<>();
        JavaMethods.getMethodCallsFromSelf(input, calls);
        return calls.stream().anyMatch(call -> call.getTargetOwner().isAssignableTo(Repository.class));
    }
}
