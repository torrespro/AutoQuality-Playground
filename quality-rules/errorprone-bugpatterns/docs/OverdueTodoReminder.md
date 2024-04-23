## BugPattern: OverdueTodoReminder

This BugPattern detects TODO comments in Java source code and sends reminders if more than 30 days have passed since the TODO was created.

### Summary

The `OverdueTodoReminder` BugPattern identifies TODO comments that have surpassed their designated completion date. It assists in enforcing timely completion of tasks by notifying developers when overdue TODOs are present in the codebase.

### Example

Consider the following example:

```java
// TODO 2023-12-31 Implement error handling
public class MyClass {
    // Code implementation
}
```

If the current date is after December 31, 2023 (by more than 30 days), the `OverdueTodoReminder` will issue a warning about the overdue TODO comment.

### How to Fix

To resolve the `OverdueTodoReminder` warning, consider the following actions:

1. **Address the TODO**: Evaluate the task associated with the TODO comment and take appropriate action to complete or remove it.
2. **Update the Deadline**: If the task is still relevant but unfinished, update the TODO comment with a revised completion date.
3. **Remove Unused TODOs**: Remove obsolete or unnecessary TODO comments from the codebase.

### Suppression

Suppress the `OverdueTodoReminder` warning using `@SuppressWarnings("OverdueTodoReminder")` on the line containing the TODO comment:

```java
@SuppressWarnings("OverdueTodoReminder")
// TODO 2023-12-31 Implement error handling
public class MyClass {
    // Code implementation
}
```

Apply the suppression annotation directly above the TODO comment to acknowledge and suppress the warning.

### Severity

The severity level for `OverdueTodoReminder` is set to `WARNING`, indicating a non-critical issue that should be addressed for code maintenance and cleanliness.
