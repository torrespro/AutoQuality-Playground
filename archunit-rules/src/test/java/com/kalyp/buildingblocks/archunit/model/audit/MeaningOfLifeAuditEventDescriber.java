package com.kalyp.buildingblocks.archunit.model.audit;

import com.kalyp.buildingblocks.archunit.model.repository.PersonRepository;
import com.kalyp.buildingblocks.archunit.model.service.LifeService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MeaningOfLifeAuditEventDescriber {

    private final LifeService lifeService;
    private final PersonRepository personRepository;

    public MeaningOfLifeAuditEventDescriber(LifeService lifeService,
        PersonRepository personRepository) {
        this.lifeService = lifeService;
        this.personRepository = personRepository;
    }
}
