package com.kalyp.buildingblocks.archunit.model.service;

import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HealthMonitoringService {

    private static final Logger logger = LoggerFactory.getLogger(HealthMonitoringService.class);

    private HealthCareService healthCareService;

    @PreAuthorize("")
    @Transactional
    public void customerPassedAway() {
        logger.info("Closing health contract because customer has passed away at {}", LocalDate.now());
        healthCareService.closeContract();
    }
}
