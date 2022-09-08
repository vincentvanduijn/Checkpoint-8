package com.devoteam.VehicleApplication.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VehicleType {
    String vehicleType;
    int id;

    @Override
    public String toString() {
        return "Vehicle type: " + vehicleType + '\n' +
                "Vehicle type ID: " + id
                ;
    }
}
