package com.devoteam.VehicleApplication.controller;

import com.devoteam.VehicleApplication.domain.Vehicle;
import com.devoteam.VehicleApplication.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("vehicles")
@Slf4j
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<Page<Vehicle>> listAll(Pageable pageable) {
        return ResponseEntity.ok(vehicleService.listAll(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Vehicle> findById(@PathVariable int id) {
        return ResponseEntity.ok(vehicleService.findById(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Vehicle>> findByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(vehicleService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Vehicle> save(@RequestBody @Valid Vehicle vehicle) {
        return ResponseEntity.ok(vehicleService.save(vehicle));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Vehicle> delete(@PathVariable int id) {
        vehicleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Vehicle> update(@RequestBody Vehicle vehicle) {
        vehicleService.update(vehicle);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
