package com.devoteam.VehicleApplication.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
// Zoeken juiste annotation voor alle constructors
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date createdOn;
    @ManyToOne
    @JoinColumn(name = "automaker_ID")
    private Automaker automaker;
    private String name;
    private String color;
    private int year;
    @ManyToOne
    @JoinColumn(name = "type_ID")
    private VehicleType vehicleType;

//    public VehicleType getVehicleType() {
//        return vehicleType;
//    }
//
//    public void setVehicleType(VehicleType vehicleType) {
//        this.vehicleType = vehicleType;
//    }
//
//    @Override
//    public String toString() {
//        return  "\n#------------------------------------------------#\n" +
//                "Model: " + name + "\n" +
//                "Id Vehicle: " + id + '\n' +
//                automaker + '\n' +
//                vehicleType + "\n" +
//                "Color: " + color + "\n" +
//                "Year: " + year + "\n" +
//                "Created on: " + createdOn + "\n"
//                ;
//    }
}
