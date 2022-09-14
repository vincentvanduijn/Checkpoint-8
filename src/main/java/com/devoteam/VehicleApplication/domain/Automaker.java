package com.devoteam.VehicleApplication.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Automaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;


//    @Override
//    public String toString() {
//        return "Automaker " +
//                "Name: " + name + '\n' +
//                "Automaker ID: " + id
//                ;
//    }
}
