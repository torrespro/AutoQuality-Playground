package com.kalyp.buildingblocks.archunit.model.packageviolation;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public class ViolationMapper {

}
