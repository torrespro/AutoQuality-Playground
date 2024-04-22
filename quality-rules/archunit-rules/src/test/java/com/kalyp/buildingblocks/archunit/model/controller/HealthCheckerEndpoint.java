package com.kalyp.buildingblocks.archunit.model.controller;

import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckerEndpoint {

    public ResponseEntity<Void> updateIndicator(@NotNull @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime time) {
        return ResponseEntity.noContent().build();
    }
}
