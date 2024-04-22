package com.kalyp.buildingblocks.archunit.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface HealthIndicatorMapping {
}
