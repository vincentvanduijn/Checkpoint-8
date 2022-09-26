package com.devoteam.VehicleApplication.util;

import com.devoteam.VehicleApplication.domain.Automaker;
import com.devoteam.VehicleApplication.domain.Vehicle;
import com.devoteam.VehicleApplication.domain.VehicleType;

import java.util.Date;

public class VehicleCreator {


    public static Vehicle createVehicleToBeSaved() {
        return Vehicle.builder()
                .model("TestCar")
                .id(1)
                .year(2000)
                .color("Red")
                .createdOn(new Date())
                .automaker(new Automaker())
                .vehicleType(new VehicleType())
                .build();
    }

    public static Vehicle createValidVehicle() {
        return Vehicle.builder()
                .model("TestCar")
                .id(1)
                .year(2000)
                .color("Red")
                .createdOn(new Date())
                .automaker(new Automaker())
                .vehicleType(new VehicleType())
                .build();
    }

    public static Vehicle createValidUpdatedVehicle() {
        return Vehicle.builder()
                .model("TestCar")
                .id(1)
                .year(2000)
                .color("Red")
                .createdOn(new Date())
                .automaker(new Automaker())
                .vehicleType(new VehicleType())
                .build();
    }
}
