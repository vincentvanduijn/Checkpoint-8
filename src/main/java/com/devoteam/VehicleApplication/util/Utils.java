package com.devoteam.VehicleApplication.util;

import com.devoteam.VehicleApplication.domain.Vehicle;
import com.devoteam.VehicleApplication.repository.VehicleRep;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class Utils {

    public Vehicle findVehicle (int id, VehicleRep vehicleRep) {
        return vehicleRep
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No vehicle(s) found with the given ID"));
    }
}
