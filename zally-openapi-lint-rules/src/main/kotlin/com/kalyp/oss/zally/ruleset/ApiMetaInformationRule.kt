package com.kalyp.oss.zally.ruleset

import org.zalando.zally.core.toJsonPointer
import org.zalando.zally.rule.api.Check
import org.zalando.zally.rule.api.Context
import org.zalando.zally.rule.api.Rule
import org.zalando.zally.rule.api.Severity
import org.zalando.zally.rule.api.Violation

@Rule(
    ruleSet = KalypRuleSet::class,
    id = "001",
    severity = Severity.MUST,
    title = "Contain API Meta Information"
)
class ApiMetaInformationRule {

    @Check(severity = Severity.MUST)
    fun checkInfoTitle(context: Context): Violation? =
        if (context.api.info?.title.isNullOrBlank()) {
            context.violation("Title has to be provided", "/openapi/info/title".toJsonPointer())
        } else null

}
