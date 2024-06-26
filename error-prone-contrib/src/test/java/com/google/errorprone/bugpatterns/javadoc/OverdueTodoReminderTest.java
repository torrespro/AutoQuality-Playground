package com.google.errorprone.bugpatterns.javadoc;

import com.google.errorprone.CompilationTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OverdueTodoReminderTest {

    private CompilationTestHelper compilationTestHelper;

    @BeforeEach
    public void setUp() {
        compilationTestHelper = CompilationTestHelper.newInstance(OverdueTodoReminder.class, getClass());
    }

    @Test
    public void testPositiveCase() {
        AssertionError expected =
                assertThrows(
                        AssertionError.class,
                        () ->
                                compilationTestHelper
                                        .addSourceLines(
                                                "Test.java", //
                                                "interface Test {",
                                                "  /**",
                                                "   // BUG: Diagnostic contains: X",
                                                "   * TODO 2024-01-01 This is a test TODO comment",
                                                "   */",
                                                "  int foo(int p) throws Exception;",
                                                "}")
                                        .expectErrorMessage("X", msg -> msg.contains("more than 30 days have passed"))
                                        .doTest());
        assertThat(expected).hasMessageThat().contains("more than 30 days have passed");

    }

    @Test
    public void testNegativeCase() {
        compilationTestHelper
                .addSourceLines(
                        "Test.java", //
                        "interface Test {",
                        "  /**",
                        "   * TODO 2024-12-01 This is another test TODO comment",
                        "   */",
                        "  int foo(int p) throws Exception;",
                        "}")
                .expectNoDiagnostics()
                .doTest();
    }

}
