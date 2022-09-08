package com.devoteam.VehicleApplication.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Automaker {
    String name;
    int id;

    @Override
    public String toString() {
        return "Automaker " +
                "Name: " + name + '\n' +
                "Automaker ID: " + id
                ;
    }
}
