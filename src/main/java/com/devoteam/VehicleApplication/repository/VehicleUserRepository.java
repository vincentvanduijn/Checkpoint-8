package com.devoteam.VehicleApplication.repository;

import com.devoteam.VehicleApplication.domain.VehicleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleUserRepository extends JpaRepository<VehicleUser, Integer> {

    VehicleUser findByUsername(String username);
}
