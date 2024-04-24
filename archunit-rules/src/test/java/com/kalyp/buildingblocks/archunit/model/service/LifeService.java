package com.kalyp.buildingblocks.archunit.model.service;

import com.kalyp.buildingblocks.archunit.model.Person;

public interface LifeService {

    /**
     * Calculates life's meaning for the specified person.
     *
     * @param person Person to calculate life's meaning for.
     * @return Specified person's life meaning.
     */
    String calculateMeaningOfLife(Person person);

    /**
     * Makes a person be born.
     */
    void beBorn();

    /**
     * Makes a person grow up.
     */
    void growUp();

    /**
     * Makes a person retire.
     */
    void retire();
}
