package com.kalyp.buildingblocks.archunit.model.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

@Endpoint(id = "arms")
public class ArmEndpoint {

    @WriteOperation
    public void moveLeftArm() {
        // to be implemented
    }
}
