package com.kalyp.oss.zally.ruleset

import com.typesafe.config.Config
import org.zalando.zally.core.toJsonPointer
import org.zalando.zally.rule.api.*

@Rule(
        ruleSet = com.kalyp.oss.zally.ruleset.KalypRuleSet::class,
        id = "B004",
        severity = Severity.MUST,
        title = "Check info block tags allowed."
)
class InfoBlockTagsChecker(config: Config) {

    private val productTags = config
            .getStringList("InfoBlockTagsChecker.productTags")
            .toSet()
    private val informativeTags = config
            .getStringList("InfoBlockTagsChecker.informativeTags")
            .toSet()

    @Check(Severity.MUST)
    fun validate(context: Context): List<Violation>  {
        val validTags = productTags.union(informativeTags)
        if(context.api.tags == null) {
            return listOf(context.violation("tags are required", "/openapi/info/tags".toJsonPointer()))
        }
        return context.api.tags.filter { tag -> !validTags.contains(tag.name)
                }.map {
                    context.violation("tag name is not allowed: ${it.name}. Only $validTags are allowed", context.api.tags)
                }
    }
}