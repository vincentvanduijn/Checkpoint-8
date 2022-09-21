package com.devoteam.VehicleApplication.service;

import com.devoteam.VehicleApplication.domain.Vehicle;
import com.devoteam.VehicleApplication.repository.VehicleRepository;
import com.devoteam.VehicleApplication.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Log4j2
@Repository
@RequiredArgsConstructor
public class VehicleService {

    private final Utils utils;
    private final VehicleRepository vehicleRepository;

    public List<Vehicle> findByName(String name) {
        return vehicleRepository.findByName(name);
    }

    public Vehicle findById(int id) {
        return utils.findVehicle(id, vehicleRepository);
    }
    @Transactional
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void delete(int id) {
        vehicleRepository.delete(utils.findVehicle(id, vehicleRepository));
    }

    public Vehicle update(Vehicle vehicle) {
        // nog checken of het ID bestaat voor de update plaatsvindt
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    public Page<Vehicle> listAll(Pageable pageable) {
        return vehicleRepository.findAll(pageable);
    }
}
