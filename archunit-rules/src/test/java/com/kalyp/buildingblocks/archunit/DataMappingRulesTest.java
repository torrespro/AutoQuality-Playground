package com.kalyp.buildingblocks.archunit;

import com.kalyp.buildingblocks.archunit.model.mapper.PersonMapper;
import org.junit.jupiter.api.Test;

class DataMappingRulesTest extends AbstractRulesTest {

    @Test
    void testGenerateErrorOnUnmappedTarget() {
        testRule(DataMappingRules.MAPPERS_SHOULD_GENERATE_ERROR_ON_UNMAPPED_TARGET, PersonMapper.class, 1);
    }
}
