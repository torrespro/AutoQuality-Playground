package com.kalyp.buildingblocks.archunit.model.repository;

import com.kalyp.buildingblocks.archunit.model.Person;
import com.kalyp.buildingblocks.archunit.model.service.LifeService;
import com.google.common.collect.ImmutableList;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {

    private final LifeService lifeService;

    public PersonRepository(LifeService lifeService) {
        this.lifeService = lifeService;
    }

    public List<Person> findAll() {
        Person john = new Person(1L, "John Doe");
        if (lifeService.calculateMeaningOfLife(john) == null) {
            throw new IllegalStateException("Cannot leave john without a life meaning");
        }
        return ImmutableList.of(john);
    }

    @Modifying
    public void updateBirthday() {
    }

    @Modifying
    public void updateMarriageDate(LocalDate date) {
    }
}
