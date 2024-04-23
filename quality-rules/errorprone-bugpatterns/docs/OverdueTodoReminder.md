# BugPattern: OverdueTodoReminder

This BugPattern detects TODO comments in Java source code and raises an error if more than 30 days have passed since the TODO was created.

### Summary

The `OverdueTodoReminder` BugPattern identifies overdue TODO comments, signaling critical issues that must be resolved promptly to maintain code quality and completeness.

### Example

Consider the following example:

```java
// TODO 2023-12-31 Implement error handling
public class MyClass {
    // Code implementation
}
```

If the current date is after December 31, 2023 (by more than 30 days), the `OverdueTodoReminder` will raise an error due to the overdue TODO comment.

### How to Fix

To resolve the `OverdueTodoReminder` error, take immediate action on the associated task:

1. **Address the TODO**: Complete the task specified by the TODO comment.
2. **Remove or Update**: If the task is no longer relevant, remove the TODO comment. Otherwise, update it with a revised completion date or action plan.

### Suppression

Suppressing the `OverdueTodoReminder` error is discouraged due to its critical nature. However, if necessary, use `@SuppressWarnings("OverdueTodoReminder")` directly above the TODO comment to acknowledge and suppress the error.

```java
@SuppressWarnings("OverdueTodoReminder")
// TODO 2023-12-31 Implement error handling
public class MyClass {
    // Code implementation
}
```

### Severity

The severity level for `OverdueTodoReminder` is set to `ERROR`, indicating that overdue TODO comments will prevent successful compilation of the code until addressed. This ensures timely resolution of tasks and maintains code quality.