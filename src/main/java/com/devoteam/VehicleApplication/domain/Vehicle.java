package com.devoteam.VehicleApplication.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Vehicle {

    private int id;
    private Date createdOn;
    private Automaker automaker;
    private String model;
    private String color;
    private int year;
    private VehicleType vehicleType;

    public Vehicle(int id, Date createdOn, Automaker automaker, String model, String color, int year, VehicleType vehicleType) {
        this.id = id;
        this.createdOn = createdOn;
        this.automaker = automaker;
        this.model = model;
        this.color = color;
        this.year = year;
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return  "\n#------------------------------------------------#\n" +
                "Model: " + model + "\n" +
                "Id Vehicle: " + id + '\n' +
                automaker + '\n' +
                vehicleType + "\n" +
                "Color: " + color + "\n" +
                "Year: " + year + "\n" +
                "Created on: " + createdOn + "\n"
                ;
    }
}
