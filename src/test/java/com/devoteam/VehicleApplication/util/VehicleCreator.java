package com.devoteam.VehicleApplication.util;

import com.devoteam.VehicleApplication.domain.Vehicle;

public class VehicleCreator {

    public static Vehicle createVehicleToBeSaved() {
        return Vehicle.builder()
                .name("TestCar")
                .build();
    }

    public static Vehicle createValidVehicle() {
        return Vehicle.builder()
                .name("TestCar")
                .id(1)
                .build();
    }

    public static Vehicle createValidUpdatedVehicle() {
        return Vehicle.builder()
                .name("TestCar")
                .id(1)
                .build();
    }
}
