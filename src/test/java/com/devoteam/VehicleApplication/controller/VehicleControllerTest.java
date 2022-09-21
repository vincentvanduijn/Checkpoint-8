package com.devoteam.VehicleApplication.controller;

import com.devoteam.VehicleApplication.domain.Vehicle;
import com.devoteam.VehicleApplication.service.VehicleService;
import com.devoteam.VehicleApplication.util.VehicleCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
class VehicleControllerTest {
    @InjectMocks
    private VehicleController vehicleController;
    @Mock
    private VehicleService vehicleServiceMock;

    @BeforeEach
    void setUp() {
        PageImpl<Vehicle> vehiclePage = new PageImpl<>(List.of(VehicleCreator.createValidVehicle()));
        BDDMockito.when(vehicleServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(vehiclePage);

        BDDMockito.when(vehicleServiceMock.findById(ArgumentMatchers.anyInt()))
                .thenReturn(VehicleCreator.createValidVehicle());

        BDDMockito.when(vehicleServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(VehicleCreator.createValidVehicle()));

        BDDMockito.when(vehicleServiceMock.save(VehicleCreator.createVehicleToBeSaved()))
                .thenReturn(VehicleCreator.createValidVehicle());

        BDDMockito.doNothing().when(vehicleServiceMock).delete(ArgumentMatchers.anyInt());

        BDDMockito.doNothing().when(vehicleServiceMock).update(VehicleCreator.createValidUpdatedVehicle());
        // Lees de comment onder William ze video (video 28)
    }

    @Test
    @DisplayName("listAll returns a pageable list of vehicles when successful")
    void listAll_ReturnListOfVehiclesInsidePageObject_WhenSuccessful() {
        String expectedName = VehicleCreator.createValidVehicle().getName();

        Page<Vehicle> vehiclePage = vehicleController.listAll(null).getBody();

        Assertions.assertThat(vehiclePage).isNotNull();

        Assertions.assertThat(vehiclePage.toList()).isNotEmpty();

        Assertions.assertThat(vehiclePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns an vehicle when successful")
    void findById_ReturnListOfVehiclesInsidePageObject_WhenSuccessful() {
        Integer expectedId = VehicleCreator.createValidVehicle().getId();

        Vehicle vehicle = vehicleController.findById(1).getBody();

        Assertions.assertThat(vehicle).isNotNull();

        Assertions.assertThat(vehicle.getId()).isNotNull();

        Assertions.assertThat(vehicle.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByName returns a list of vehicles when successful")
    void findByName_ReturnListOfVehicles_WhenSuccessful() {
        String expectedName = VehicleCreator.createValidVehicle().getName();

        List<Vehicle> vehicleList = vehicleController.findByName("Malibu").getBody();

        Assertions.assertThat(vehicleList).isNotNull();

        Assertions.assertThat(vehicleList).isNotEmpty();

        Assertions.assertThat(vehicleList.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("save creates an vehicle when successful")
    void save_CreatesVehicle_WhenSuccessful() {
        Integer expectedId = VehicleCreator.createValidVehicle().getId();

        Vehicle vehicleToBeSaved = VehicleCreator.createVehicleToBeSaved();

        Vehicle vehicle = vehicleController.save(vehicleToBeSaved).getBody();

        Assertions.assertThat(vehicle).isNotNull();

        Assertions.assertThat(vehicle.getId()).isNotNull();

        Assertions.assertThat(vehicle.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("delete removes the vehicle when successful")
    void delete_RemovesVehicle_WhenSuccessful() {

        ResponseEntity<Vehicle> responseEntity = vehicleController.delete(1);

        Assertions.assertThat(responseEntity).isNotNull();

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThat(responseEntity.getBody()).isNull();
    }

    @Test
    @DisplayName("Updates the vehicle when successful")
    void update_UpdatesVehicle_WhenSuccessful() {

        ResponseEntity<Vehicle> responseEntity = vehicleController.update(VehicleCreator.createValidUpdatedVehicle());

        Assertions.assertThat(responseEntity).isNotNull();

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThat(responseEntity.getBody()).isNull();
    }

}