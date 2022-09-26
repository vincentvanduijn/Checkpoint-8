package com.devoteam.VehicleApplication.client;

import com.devoteam.VehicleApplication.domain.Vehicle;
import com.devoteam.VehicleApplication.wrapper.PageableResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;


@Log4j2
public class SpringClient {

    public static void main(String[] args) {
        log.info(new BCryptPasswordEncoder().encode("root"));


        ResponseEntity<PageableResponse<Vehicle>> exchangeVehicle = new RestTemplate()
                .exchange("http://localhost:8080/vehicles", HttpMethod.GET, null,
                        new ParameterizedTypeReference<PageableResponse<Vehicle>>() {
                });

        log.info("Vehicle list {}" , exchangeVehicle.getBody());

        Vehicle swapCar = Vehicle.builder().model("SwapCar").build();
        Vehicle swipeCar = Vehicle.builder().model("SwipeCar").build();

        Vehicle swipeCarSaved = new RestTemplate()
                .exchange("http://localhost:8080/vehicles", HttpMethod.POST, new HttpEntity<>(swipeCar, createJsonHeader()), Vehicle.class).getBody();

        log.info("SwipeCar saved id: {}", swipeCarSaved.getId());

        swipeCarSaved.setModel("SwipeCar 2.0");
        ResponseEntity<Void> updatedExchange = new RestTemplate()
                .exchange("http://localhost:8080/vehicles", HttpMethod.POST, new HttpEntity<>(swipeCarSaved, createJsonHeader()), Void.class);

        log.info("SwipceCar updated status: {}", updatedExchange.getStatusCode());

        ResponseEntity<Void> deletedExchange = new RestTemplate()
                .exchange("http://localhost:8080/vehicles/{id}", HttpMethod.DELETE, null, Void.class, swipeCarSaved.getId());

        log.info("SwipeCar deleted: {}", deletedExchange.getStatusCode());
    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
