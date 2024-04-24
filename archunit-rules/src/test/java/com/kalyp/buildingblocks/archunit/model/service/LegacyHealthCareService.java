package com.kalyp.buildingblocks.archunit.model.service;

import java.time.LocalDate;
import java.util.List;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Transactional
@Validated
public class LegacyHealthCareService {

    private static final Logger logger = LoggerFactory.getLogger(LegacyHealthCareService.class);

    @Transactional(TxType.SUPPORTS)
    public Integer avgHeartRate(@NotNull @Size(min = 1) List<Integer> heartRateValues) {
        logger.info("Calculating average of heart rate values at {}", LocalDate.now());
        return heartRateValues.stream().mapToInt(i -> i).sum() / heartRateValues.size();
    }

    public void closeContract() {
        logger.info("Closing contract at {}", LocalDate.now());
        // closing contract in the DB
    }
}
