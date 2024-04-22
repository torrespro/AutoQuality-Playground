package com.kalyp.buildingblocks.archunit.model.service;

import com.kalyp.buildingblocks.archunit.model.persistence.LifeStore;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HealthCareService {

    private static final Logger logger = LoggerFactory.getLogger(HealthCareService.class);

    private LifeStore lifeStore;

    @PreAuthorize("")
    @Transactional(readOnly = true)
    public void validateUsage() {
        logger.info("Validating usage of contract at {}", LocalDate.now());
        lifeStore.existsById(1);
    }

    @PreAuthorize("")
    public void closeContract() {
        logger.info("Closing contract at {}", LocalDate.now());
        lifeStore.count();
    }
}
