package com.kalyp.buildingblocks.archunit.model.service;

import com.kalyp.buildingblocks.archunit.model.Person;

public interface HealthService {

    /**
     * Checks whether the specified person is healthy.
     *
     * @param person Person to check.
     * @return True if the person is healthy.
     */
    boolean isHealthy(Person person);

    /**
     * Gets a lot of medicine for a person.
     */
    void getLotsOfMedicine();
}
