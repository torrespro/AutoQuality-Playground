package com.kalyp.buildingblocks.archunit.model.service;

import com.kalyp.buildingblocks.archunit.model.Person;
import com.kalyp.buildingblocks.archunit.model.persistence.LifeStore;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LifeServiceImpl implements LifeService {

    private Logger logger = LoggerFactory.getLogger(LifeServiceImpl.class);

    private HealthService healthService;
    private LifeStore lifeStore;

    @PostConstruct
    public void init() {
        logger.info("Initializing with [{}] basis points on [{}] with [{}]", 10, new Date(), Calendar.getInstance());
    }

    @Override
    @PreAuthorize("")
    public String calculateMeaningOfLife(Person person) {
        logger.info("This method is really hard to implement");
        return null;
    }

    @Override
    @PreAuthorize("")
    public void beBorn() {
        logger.info("New person being born at {}", LocalDate.now());
        lifeStore.save(new Person(1L, "you"));
        // rest of implementation is up to you
    }

    @Override
    @PreAuthorize("")
    public void growUp() {
        logger.info("Person growing up at {}", LocalDate.now());
        lifeStore.count();
    }

    @Override
    @PreAuthorize("")
    public void retire() {
        logger.info("Person retiring at {}", LocalDate.now());
        healthService.getLotsOfMedicine();
    }
}
