package com.devoteam.VehicleApplication.util;

import com.devoteam.VehicleApplication.domain.Vehicle;
import com.devoteam.VehicleApplication.exception.ResourceNotFoundException;
import com.devoteam.VehicleApplication.repository.VehicleRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class Utils {

    public Vehicle findVehicle(int id, VehicleRepository vehicleRepository) {
        return vehicleRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No vehicle(s) found with the given ID"));
    }
}
