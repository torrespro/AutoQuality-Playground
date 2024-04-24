package com.kalyp.buildingblocks.archunit.model.rest;

import com.kalyp.buildingblocks.archunit.model.Person;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameOfLifeController {

    private static final Logger logger = LoggerFactory.getLogger(GameOfLifeController.class);

    ResponseEntity<String> beBorn(@NotNull LocalDate birthday) {
        logger.info("A new person was born on [{}]", new SimpleDateFormat().format(birthday));
        return ResponseEntity.created(URI.create("http://thegreatunknown.org")).build();
    }

    ResponseEntity<String> getMarried(Person partner) {
        logger.info("A new marriage has begun with [{}]", partner);
        return ResponseEntity.created(URI.create("http://marriages.org/marriages/an-id")).build();
    }
}
