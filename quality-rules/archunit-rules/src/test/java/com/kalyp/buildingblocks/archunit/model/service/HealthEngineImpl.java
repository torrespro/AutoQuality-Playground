package com.kalyp.buildingblocks.archunit.model.service;

import com.kalyp.buildingblocks.archunit.model.Person;
import com.kalyp.buildingblocks.archunit.model.persistence.LifeStore;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HealthEngineImpl implements HealthService {

    private static final Logger logger = LoggerFactory.getLogger(HealthEngineImpl.class);

    private final LifeStore lifeStore;

    public HealthEngineImpl(LifeStore lifeStore) {
        this.lifeStore = lifeStore;
    }

    @Override
    public boolean isHealthy(Person person) {
        List<BigDecimal> indicators = new ArrayList<>();
        indicators.add(BigDecimal.ONE);
        // calculate a bunch of health indicators
        return indicators.stream().allMatch(indicator -> indicator.compareTo(BigDecimal.TEN) < 0);
    }

    @Override
    @PreAuthorize("")
    @Transactional(readOnly = true)
    public void getLotsOfMedicine() {
        logger.info("Obtaining lots of medicine for a person at {}", LocalDate.now());
        lifeStore.count();
    }
}
