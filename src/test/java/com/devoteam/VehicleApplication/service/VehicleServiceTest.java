package com.devoteam.VehicleApplication.service;

import com.devoteam.VehicleApplication.domain.Vehicle;
import com.devoteam.VehicleApplication.exception.ResourceNotFoundException;
import com.devoteam.VehicleApplication.repository.VehicleRepository;
import com.devoteam.VehicleApplication.util.Utils;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class VehicleServiceTest {

    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleRepository vehicleRepositoryMock;

    @Mock
    private Utils utilsMock;

    @BeforeEach
    void setUp() {
        PageImpl<Vehicle> vehiclePage = new PageImpl<>(List.of(VehicleCreator.createValidVehicle()));
        BDDMockito.when(vehicleRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(vehiclePage);

        BDDMockito.when(vehicleRepositoryMock.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(VehicleCreator.createValidVehicle()));

        BDDMockito.when(vehicleRepositoryMock.findByModel(ArgumentMatchers.anyString()))
                .thenReturn(List.of(VehicleCreator.createValidVehicle()));

        BDDMockito.when(vehicleRepositoryMock.save(VehicleCreator.createVehicleToBeSaved()))
                .thenReturn(VehicleCreator.createValidVehicle());

        BDDMockito.doNothing().when(vehicleRepositoryMock).delete(ArgumentMatchers.any(Vehicle.class));

        BDDMockito.when(vehicleRepositoryMock.save(VehicleCreator.createValidVehicle()))
                .thenReturn(VehicleCreator.createValidUpdatedVehicle());

        BDDMockito.when(utilsMock.findVehicle(ArgumentMatchers.anyInt(), ArgumentMatchers.any(VehicleRepository.class)))
                .thenReturn(VehicleCreator.createValidUpdatedVehicle());
    }

    @Test
    @DisplayName("listAll returns a pageable list of vehicles when successful")
    void listAll_ReturnListOfVehiclesInsidePageObject_WhenSuccessful() {
        String expectedName = VehicleCreator.createValidVehicle().getModel();

        Page<Vehicle> vehiclePage = vehicleService.listAll(PageRequest.of(1, 1));

        Assertions.assertThat(vehiclePage).isNotNull();
        Assertions.assertThat(vehiclePage.toList()).isNotEmpty();
        Assertions.assertThat(vehiclePage.toList().get(0).getModel()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns an vehicle when successful")
    void findById_ReturnListOfVehiclesInsidePageObject_WhenSuccessful() {
        Integer expectedId = VehicleCreator.createValidVehicle().getId();

        Vehicle vehicle = vehicleService.findById(1);

        Assertions.assertThat(vehicle).isNotNull();
        Assertions.assertThat(vehicle.getId()).isNotNull();
        Assertions.assertThat(vehicle.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByName returns a pageable list of vehicles when successful")
    void findByName_ReturnListOfVehiclesInsidePageObject_WhenSuccessful() {
        String expectedName = VehicleCreator.createValidVehicle().getModel();

        List<Vehicle> vehicleList = vehicleService.findByModel("Malibu");

        Assertions.assertThat(vehicleList).isNotNull();
        Assertions.assertThat(vehicleList).isNotEmpty();
        Assertions.assertThat(vehicleList.get(0).getModel()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("save creates an vehicle when successful")
    void save_CreatesVehicle_WhenSuccessful() {
        Integer expectedId = VehicleCreator.createValidVehicle().getId();
        Vehicle vehicleToBeSaved = VehicleCreator.createVehicleToBeSaved();

        Vehicle vehicle = vehicleService.save(vehicleToBeSaved);

        Assertions.assertThat(vehicle).isNotNull();
        Assertions.assertThat(vehicle.getId()).isNotNull();
        Assertions.assertThat(vehicle.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("delete removes the vehicle when successful")
    void delete_RemovesVehicle_WhenSuccessful() {

        Assertions.assertThatCode(() -> vehicleService.delete(1))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("delete throws ResourceNotFoundException when the vehicle does not exist")
    void delete_ThrowsResourceNotFoundException_WhenVehicleDoesNotExist() {
        BDDMockito.when(
                        utilsMock.findVehicle(ArgumentMatchers.anyInt(), ArgumentMatchers.any(VehicleRepository.class)))
                .thenThrow(new ResourceNotFoundException("Vehicle not found"));

        Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> vehicleService.delete(1));
    }

    @Test
    @DisplayName("Updates the vehicle when successful")
    void update_UpdatesVehicle_WhenSuccessful() {
        Integer expectedId = VehicleCreator.createValidUpdatedVehicle().getId();

        Vehicle vehicle = vehicleService.update(VehicleCreator.createValidUpdatedVehicle());

        Assertions.assertThat(vehicle).isNotNull();
        Assertions.assertThat(vehicle.getId()).isNotNull();
        Assertions.assertThat(vehicle.getId()).isEqualTo(expectedId);

    }
}