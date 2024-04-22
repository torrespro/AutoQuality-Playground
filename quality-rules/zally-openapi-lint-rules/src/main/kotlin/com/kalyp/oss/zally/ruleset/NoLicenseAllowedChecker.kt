package com.kalyp.oss.zally.ruleset

import org.zalando.zally.core.toJsonPointer
import org.zalando.zally.rule.api.*

@Rule(
        ruleSet = com.kalyp.oss.zally.ruleset.KalypRuleSet::class,
        id = "B001",
        severity = Severity.MUST,

        title = "No license information allowed."
)
class NoLicenseAllowedChecker() {

    @Check(Severity.MUST)
    fun validate(context: Context): List<Violation> {

        return when {
            !context.isOpenAPI3() -> emptyList()
            context.api.info.license != null ->
                listOf(Violation("OpenAPI must not contain a license  because it's covered by the License Agreement we already negotiate with customers. ", "/openapi/info/version".toJsonPointer()))
            else -> emptyList()
        }
    }
}
