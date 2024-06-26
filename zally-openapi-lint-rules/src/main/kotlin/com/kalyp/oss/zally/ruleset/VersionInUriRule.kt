package com.kalyp.oss.zally.ruleset

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.PathItem
import org.zalando.zally.rule.api.*

@Rule(
        ruleSet = com.kalyp.oss.zally.ruleset.KalypRuleSet::class,
        id = "B009",
        severity = Severity.MUST,
        title = "Check prefix for paths should contain version."
)
class VersionInUriRule {

    private val description = "URL should contain version number"
    private val versionRegex = "(.*)v[0-9]+(.*)".toRegex()

    @Check(severity = Severity.MUST)
    fun validate(context: Context): List<Violation> =
            ( violatingPaths(context.api))
                    .map { context.violation(description, it) }



    private fun violatingPaths(api: OpenAPI): Collection<PathItem> =
            api.paths.orEmpty().entries
                    .filter { (path, _) -> !path.matches(versionRegex) }
                    .map { (_, pathEntry) -> pathEntry }
}