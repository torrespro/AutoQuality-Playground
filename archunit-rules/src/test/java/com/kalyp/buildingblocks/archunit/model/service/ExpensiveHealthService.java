package com.kalyp.buildingblocks.archunit.model.service;

import com.kalyp.buildingblocks.archunit.model.Person;
import com.kalyp.buildingblocks.archunit.model.persistence.LifeStore;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ExpensiveHealthService implements HealthService {

    private LifeStore lifeStore;

    @Override
    @Transactional(readOnly = true)
    public boolean isHealthy(Person person) {
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public void getLotsOfMedicine() {
        lifeStore.findAll();
    }
}
