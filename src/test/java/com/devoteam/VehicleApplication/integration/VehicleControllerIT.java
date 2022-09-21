package com.devoteam.VehicleApplication.integration;

import com.devoteam.VehicleApplication.domain.Vehicle;
import com.devoteam.VehicleApplication.repository.VehicleRepository;
import com.devoteam.VehicleApplication.util.VehicleCreator;
import com.devoteam.VehicleApplication.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;

import java.util.List;
import java.util.Optional;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VehicleControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @MockBean
    private VehicleRepository vehicleRepositoryMock;

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    @BeforeEach
    void setUp() {
        PageImpl<Vehicle> vehiclePage = new PageImpl<>(List.of(VehicleCreator.createValidVehicle()));
        BDDMockito.when(vehicleRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(vehiclePage);

        BDDMockito.when(vehicleRepositoryMock.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(VehicleCreator.createValidVehicle()));

        BDDMockito.when(vehicleRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(VehicleCreator.createValidVehicle()));

        BDDMockito.when(vehicleRepositoryMock.save(VehicleCreator.createVehicleToBeSaved()))
                .thenReturn(VehicleCreator.createValidVehicle());

        BDDMockito.doNothing().when(vehicleRepositoryMock).delete(ArgumentMatchers.any(Vehicle.class));

        BDDMockito.when(vehicleRepositoryMock.save(VehicleCreator.createValidVehicle()))
                .thenReturn(VehicleCreator.createValidUpdatedVehicle());
    }

    @Test
    @DisplayName("listAll returns a pageable list of vehicles when successful")
    void listAll_ReturnListOfVehiclesInsidePageObject_WhenSuccessful() {
        String expectedName = VehicleCreator.createValidVehicle().getName();

        Page<Vehicle> vehiclePage = testRestTemplate.exchange("/vehicles", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Vehicle>>() {
                }).getBody();

        Assertions.assertThat(vehiclePage).isNotNull();

        Assertions.assertThat(vehiclePage.toList()).isNotEmpty();

        Assertions.assertThat(vehiclePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns an vehicle when successful")
    void findById_ReturnListOfVehiclesInsidePageObject_WhenSuccessful() {
        Integer expectedId = VehicleCreator.createValidVehicle().getId();

        Vehicle vehicle = testRestTemplate.getForObject("/vehicles/1", Vehicle.class);

        Assertions.assertThat(vehicle).isNotNull();

        Assertions.assertThat(vehicle.getId()).isNotNull();

        Assertions.assertThat(vehicle.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByName returns a list of vehicles when successful")
    void findByName_ReturnListOfVehicles_WhenSuccessful() {
        String expectedName = VehicleCreator.createValidVehicle().getName();

        List<Vehicle> vehicleList = testRestTemplate.exchange("/vehicles/find?name = 'Malibu' ",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Vehicle>>() {
                }).getBody();

        Assertions.assertThat(vehicleList).isNotNull();

        Assertions.assertThat(vehicleList).isNotEmpty();

        Assertions.assertThat(vehicleList.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("save creates an vehicle when successful")
    void save_CreatesVehicle_WhenSuccessful() {
        Integer expectedId = VehicleCreator.createValidVehicle().getId();

        Vehicle vehicleToBeSaved = VehicleCreator.createVehicleToBeSaved();

        Vehicle vehicle = testRestTemplate.exchange("/vehicles", HttpMethod.POST,
                createJsonHyypEntity(vehicleToBeSaved), Vehicle.class).getBody();

        Assertions.assertThat(vehicle).isNotNull();

        Assertions.assertThat(vehicle.getId()).isNotNull();

        Assertions.assertThat(vehicle.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("delete removes the vehicle when successful")
    void delete_RemovesVehicle_WhenSuccessful() {

        ResponseEntity<Vehicle> responseEntity = testRestTemplate.exchange("vehicles/1", HttpMethod.DELETE,
                null, Vehicle.class);

        Assertions.assertThat(responseEntity).isNotNull();

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThat(responseEntity.getBody()).isNull();
    }

    @Test
    @DisplayName("Updates the vehicle when successful")
    void update_UpdatesVehicle_WhenSuccessful() {
        Vehicle validVehicle = VehicleCreator.createValidVehicle();

        ResponseEntity<Vehicle> responseEntity = testRestTemplate.exchange("/vehicles", HttpMethod.PUT,
                createJsonHyypEntity(validVehicle), Vehicle.class);

        Assertions.assertThat(responseEntity).isNotNull();

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThat(responseEntity.getBody()).isNull();
    }

    private HttpEntity<Vehicle> createJsonHyypEntity(Vehicle vehicle) {
        return new HttpEntity<>(vehicle, createJsonHeader());
    }
}