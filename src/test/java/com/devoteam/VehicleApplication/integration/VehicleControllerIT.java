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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
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
    @Qualifier(value = "testRestTemplateRoleUser")
    private TestRestTemplate testRestTemplateUser;

    @Autowired
    @Qualifier(value = "testRestTemplateRoleAdmin")
    private TestRestTemplate testRestTemplateAdmin;

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

        BDDMockito.when(vehicleRepositoryMock.findByModel(ArgumentMatchers.anyString()))
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
        String expectedName = VehicleCreator.createValidVehicle().getModel();

        Page<Vehicle> vehiclePage = testRestTemplateUser.exchange("/vehicles", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Vehicle>>() {
                }).getBody();

        Assertions.assertThat(vehiclePage).isNotNull();
        Assertions.assertThat(vehiclePage.toList()).isNotEmpty();
        Assertions.assertThat(vehiclePage.toList().get(0).getModel()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns an vehicle when successful")
    void findById_ReturnListOfVehiclesInsidePageObject_WhenSuccessful() {
        Integer expectedId = VehicleCreator.createValidVehicle().getId();

        Vehicle vehicle = testRestTemplateUser.getForObject("/vehicles/1", Vehicle.class);

        Assertions.assertThat(vehicle).isNotNull();
        Assertions.assertThat(vehicle.getId()).isNotNull();
        Assertions.assertThat(vehicle.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByName returns a list of vehicles when successful")
    void findByName_ReturnListOfVehicles_WhenSuccessful() {
        String expectedName = VehicleCreator.createValidVehicle().getModel();

        List<Vehicle> vehicleList = testRestTemplateUser.exchange("/vehicles/find?name='TestCar'",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Vehicle>>() {
                }).getBody();

        Assertions.assertThat(vehicleList).isNotNull();
        Assertions.assertThat(vehicleList).isNotEmpty();
        Assertions.assertThat(vehicleList.get(0).getModel()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("save creates an vehicle when successful")
    void save_CreatesVehicle_WhenSuccessful() {
        Integer expectedId = VehicleCreator.createValidVehicle().getId();
        Vehicle vehicleToBeSaved = VehicleCreator.createVehicleToBeSaved();

        Vehicle vehicle = testRestTemplateAdmin.exchange("/vehicles", HttpMethod.POST,
                createJsonHttpEntity(vehicleToBeSaved), Vehicle.class).getBody();

        Assertions.assertThat(vehicle).isNotNull();
        Assertions.assertThat(vehicle.getId()).isNotNull();
        Assertions.assertThat(vehicle.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("delete removes the vehicle when successful")
    void delete_RemovesVehicle_WhenSuccessful() {
        ResponseEntity<Void> responseEntity = testRestTemplateAdmin.exchange("http://localhost:8080/vehicles/admin/2", HttpMethod.DELETE,
                null, Void.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        Assertions.assertThat(responseEntity.getBody()).isNull();
    }

    @Test
    @DisplayName("delete returns forbidden when user does not have the role admin")
    void delete_Returns403_WhenUserIsNotAdmin() {
        ResponseEntity<Void> responseEntity = testRestTemplateAdmin.exchange("http://localhost:8080/vehicles/admin/1", HttpMethod.DELETE,
                null, Void.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    @DisplayName("Updates the vehicle when successful")
    void update_UpdatesVehicle_WhenSuccessful() {
        Vehicle validVehicle = VehicleCreator.createValidVehicle();

        ResponseEntity<Void> responseEntity = testRestTemplateAdmin.exchange("/vehicles", HttpMethod.PUT,
                createJsonHttpEntity(validVehicle), Void.class);

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        Assertions.assertThat(responseEntity.getBody()).isNull();
    }

    private HttpEntity<Vehicle> createJsonHttpEntity(Vehicle vehicle) {
        return new HttpEntity<>(vehicle, createJsonHeader());
    }
    @Lazy
    @TestConfiguration
    static class Config {

        @Bean(name = "testRestTemplateRoleUser")
        public TestRestTemplate testRestTemplateRolesUserCreator(@Value("${local.server.port}") int port) {
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                    .rootUri("http://localhost:" + port)
                    .basicAuthentication("USER", "root");

            return new TestRestTemplate(restTemplateBuilder);
        }

        @Bean(name = "testRestTemplateRoleAdmin")
        public TestRestTemplate testRestTemplateRolesAdminCreator(@Value("${local.server.port}") int port) {
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                    .rootUri("http://localhost:" + port)
                    .basicAuthentication("ADMIN", "root");

            return new TestRestTemplate(restTemplateBuilder);
        }
    }
}