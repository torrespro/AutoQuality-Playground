package com.google.errorprone.bugpatterns.javadoc;

import com.google.auto.service.AutoService;
import com.google.errorprone.BugPattern;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.matchers.Description;
import com.sun.source.doctree.DocCommentTree;
import com.sun.source.doctree.DocTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.VariableTree;
import com.sun.source.util.DocTreePath;
import com.sun.tools.javac.util.JCDiagnostic;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AutoService(BugChecker.class)
@BugPattern(
    name = "OverdueTodoReminder",
    linkType = BugPattern.LinkType.CUSTOM,
    link = "https://github.com/torrespro/AutoQuality-Playground/blob/main/quality-rules/errorprone-bugpatterns/docs/OverdueTodoReminder.md",
    summary = "Finds TODO comments and sends a reminder if more than 30 days have passed",
    severity = BugPattern.SeverityLevel.ERROR
)
public class OverdueTodoReminder extends BugChecker implements BugChecker.ClassTreeMatcher, BugChecker.MethodTreeMatcher, BugChecker.VariableTreeMatcher {

    private static final Pattern TODO_PATTERN = Pattern.compile("TODO (\\d{4}-\\d{2}-\\d{2})");

    @Override
    public Description matchClass(ClassTree classTree, VisitorState state) {
        return checkTodos(state);
    }

    @Override
    public Description matchMethod(MethodTree methodTree, VisitorState state) {
        return checkTodos(state);
    }

    @Override
    public Description matchVariable(VariableTree variableTree, VisitorState state) {
        return checkTodos(state);
    }

    private Description checkTodos(VisitorState state) {
        DocTreePath docTreePath = Utils.getDocTreePath(state);
        if (docTreePath != null) {
            DocCommentTree docCommentTree = docTreePath.getDocComment();
            if (docCommentTree != null) {
                List<? extends DocTree> commentTags = docCommentTree.getFullBody();
                for (DocTree tag : commentTags) {
                    String comment = tag.toString();
                    Matcher matcher = TODO_PATTERN.matcher(comment);
                    if (matcher.find()) {
                        LocalDate todoDate = LocalDate.parse(matcher.group(1));
                        LocalDate now = LocalDate.now();
                        long daysBetween = ChronoUnit.DAYS.between(todoDate, now);
                        if (daysBetween > 30) {
                            JCDiagnostic.DiagnosticPosition diagnosticPosition = Utils.diagnosticPosition(docTreePath, state);
                            return describeMatch(diagnosticPosition);
                        }
                    }
                }
            }
        }
        return Description.NO_MATCH;
    }
}
