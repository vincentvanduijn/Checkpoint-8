package com.devoteam.VehicleApplication.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String vehicleType;
    int id;

//    @Override
//    public String toString() {
//        return "Vehicle type: " + vehicleType + '\n' +
//                "Vehicle type ID: " + id
//                ;
//    }
}
