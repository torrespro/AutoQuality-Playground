package com.kalyp.oss.zally.ruleset

import com.typesafe.config.Config
import org.zalando.zally.rule.api.*

@Rule(
        ruleSet = com.kalyp.oss.zally.ruleset.KalypRuleSet::class,
        id = "B011",
        severity = Severity.MUST,
        title = "Use Standard HTTP Status Codes"
)
class UseStandardHttpStatusCodesRule(config: Config) {

        private val standardResponseCodes = config.getStringList("${javaClass.simpleName}.standard")

        /**
         * Validate that only standardized HTTP response codes are used
         * @param context the context to validate
         * @return list of identified violations
         */
        @Check(severity = Severity.MUST)
        fun checkIfOnlyStandardizedResponseCodesAreUsed(context: Context): List<Violation> =
                context.validateOperations { (_, operation) ->
                    operation?.responses.orEmpty().filterNot { (status, _) ->
                        status in standardResponseCodes
                    }.map { (status, response) ->
                        context.violation("$status is not a standardized response code", response)
                    }
                }

}
