package com.devoteam.VehicleApplication.controller;

import com.devoteam.VehicleApplication.domain.Vehicle;
import com.devoteam.VehicleApplication.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vehicles")
@Slf4j
@RequiredArgsConstructor
public class VehicleController {

    //localhost:8080/vehicles
    private final VehicleRepository vehicleRepository;

    @GetMapping
    public ResponseEntity<List<Vehicle>> listAll() {
        return ResponseEntity.ok(vehicleRepository.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Vehicle> findById(@PathVariable int id) {
        return ResponseEntity.ok(VehicleRepository.findById(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Vehicle>> findByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(VehicleRepository.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Vehicle> save(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(VehicleRepository.save(vehicle));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Vehicle> delete(@PathVariable int id) {
        VehicleRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Vehicle> update(@RequestBody Vehicle vehicle) {
        VehicleRepository.update(vehicle);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
