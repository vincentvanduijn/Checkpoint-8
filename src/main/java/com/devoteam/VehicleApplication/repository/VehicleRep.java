package com.devoteam.VehicleApplication.repository;

import com.devoteam.VehicleApplication.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRep extends JpaRepository<Vehicle, Integer> {
    List<Vehicle> findByName(String name);
}
