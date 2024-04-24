package com.kalyp.buildingblocks.archunit.util;


import com.tngtech.archunit.core.domain.AccessTarget.MethodCallTarget;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaMethodCall;
import java.util.Set;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public class JavaMethods {

    private JavaMethods() {
    }

    /**
     * Recursively adds all methods calls done by the given one to the provided set.
     * It stops at Repository and java.* classes to avoid analyzing JDK and Spring methods.
     *
     * @param method Method to get calls for.
     * @param calls Set that'll contain all calls found.
     */
    public static void getMethodCallsFromSelf(JavaMethod method, Set<JavaMethodCall> calls) {
        for (JavaMethodCall call : method.getMethodCallsFromSelf()) {
            if (!calls.contains(call)) {
                calls.add(call);
                if (!call.getTargetOwner().isAssignableTo(Repository.class)) {
                    Optional<JavaMethod> target = call.getTarget().resolveMember();
                    if (target.isPresent()) {
                        getMethodCallsFromSelf(target.get(), calls);
                    }
                    getPolymorphicCallsIfAny(call, calls);
                }
            }
        }
    }

    private static void getPolymorphicCallsIfAny(JavaMethodCall call, Set<JavaMethodCall> calls) {
        // Go through polymorphic calls to check every possible implementation
        if (call.getTargetOwner().isInterface()
            && call.getTargetOwner().getAllSubclasses().size() > 1
            && !call.getTargetOwner().getPackageName().startsWith("java.")) {
            call.getTargetOwner().getAllSubclasses().stream()
                .map(subClass -> findMethod(subClass, call.getTarget()))
                .flatMap(Optional::stream)
                .forEach(m -> getMethodCallsFromSelf(m, calls));
        }
    }

    private static Optional<JavaMethod> findMethod(JavaClass javaClass, MethodCallTarget target) {
        Class<?>[] parameterTypes = target.getRawParameterTypes().stream()
            .map(JavaClass::reflect)
            .toArray(Class<?>[]::new);
        return javaClass.tryGetMethod(target.getName(), parameterTypes);
    }
}
