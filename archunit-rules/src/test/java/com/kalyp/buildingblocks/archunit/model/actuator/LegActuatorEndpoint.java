package com.kalyp.buildingblocks.archunit.model.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

@Endpoint(id = "legs")
public class LegActuatorEndpoint {

    @WriteOperation
    public void moveRightLeg() {
        // to be implemented
    }
}
