package com.google.errorprone.bugpatterns.javadoc;

import com.google.common.base.Predicates;
import com.google.errorprone.CompilationTestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

@RunWith(JUnit4.class)
public class OverdueTodoReminderTest {

    private CompilationTestHelper compilationTestHelper;

    @Before
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
