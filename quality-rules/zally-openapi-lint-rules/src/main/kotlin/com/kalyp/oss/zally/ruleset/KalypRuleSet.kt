package com.kalyp.oss.zally.ruleset

import org.zalando.zally.core.AbstractRuleSet
import org.zalando.zally.rule.api.Rule
import java.net.URI

class KalypRuleSet : AbstractRuleSet() {
    override val url: URI = URI.create("https://docs.kalyp.com/#/guidelines%2Fapi_rest_guidelines")

    override fun url(rule: Rule): URI {
        val heading = "${rule.id}: ${rule.title}"
        val ref = heading
            .lowercase()
            .replace(Regex("[^a-z0-9]+"), "-")
        return url.resolve("#$ref")
    }
}
