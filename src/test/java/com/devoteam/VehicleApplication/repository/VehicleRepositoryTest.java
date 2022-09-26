package com.devoteam.VehicleApplication.repository;

import com.devoteam.VehicleApplication.domain.Vehicle;
import com.devoteam.VehicleApplication.util.VehicleCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Vehicle Repository Tests")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    @DisplayName("Save creates vehicle when successful")
    void save_PersistVehicle_WhenSuccessful() {
        Vehicle vehicle = VehicleCreator.createVehicleToBeSaved();

        Vehicle savedVehicle = this.vehicleRepository.save(vehicle);
        Assertions.assertThat(savedVehicle.getId()).isNotNull();
        Assertions.assertThat(savedVehicle.getModel()).isNotNull();
        Assertions.assertThat(savedVehicle.getModel()).isEqualTo(vehicle.getModel());
    }

    @Test
    @DisplayName("Save updates vehicle when successful")
    void save_UpdateVehicle_WhenSuccessful() {
        Vehicle vehicle = VehicleCreator.createVehicleToBeSaved();
        Vehicle savedVehicle = this.vehicleRepository.save(vehicle);
        savedVehicle.setModel("GoGoCart");

        Vehicle updatedVehicle = this.vehicleRepository.save(savedVehicle);

        Assertions.assertThat(savedVehicle.getId()).isNotNull();
        Assertions.assertThat(savedVehicle.getModel()).isNotNull();
        Assertions.assertThat(savedVehicle.getModel()).isEqualTo(updatedVehicle.getModel());
    }

    @Test
    @DisplayName("Delete removes vehicle when successful")
    void delete_UpdateVehicle_WhenSuccessful() {
        Vehicle vehicle = VehicleCreator.createVehicleToBeSaved();
        Vehicle savedVehicle = this.vehicleRepository.save(vehicle);

        this.vehicleRepository.delete(vehicle);

        Optional<Vehicle> vehicleOptional = this.vehicleRepository.findById(savedVehicle.getId());
        Assertions.assertThat(vehicleOptional.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Find by name returns vehicle when successful")
    void findByName_ReturnVehicle_WhenSuccessful() {
        Vehicle vehicle = VehicleCreator.createVehicleToBeSaved();
        Vehicle savedVehicle = this.vehicleRepository.save(vehicle);
        String name = savedVehicle.getModel();

        List<Vehicle> vehicleList = this.vehicleRepository.findByModel(name);

        Assertions.assertThat(vehicleList).isNotEmpty();
        Assertions.assertThat(vehicleList).contains(savedVehicle);
    }

    @Test
    @DisplayName("Find by name returns empty vehicle list when no vehicle is found")
    void findByName_ReturnEmptyVehicleList_WhenVehicleNotFound() {
        String name = "Fake-name";

        List<Vehicle> vehicleList = this.vehicleRepository.findByModel(name);

        Assertions.assertThat(vehicleList).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when vehicle is empty")
    void save_ThrowConstraintViolationException_WhenVehicleIsEmpty() {
        Vehicle vehicle = new Vehicle();

        Assertions.assertThatExceptionOfType(DataIntegrityViolationException.class)
                .isThrownBy(() -> vehicleRepository.save(vehicle));
    }

}