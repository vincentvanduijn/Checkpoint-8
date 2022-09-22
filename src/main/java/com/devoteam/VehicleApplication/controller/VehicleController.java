package com.devoteam.VehicleApplication.controller;

import com.devoteam.VehicleApplication.domain.Vehicle;
import com.devoteam.VehicleApplication.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("vehicles")
@Log4j2
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    @Operation(description = "Request a overview of all vehicles present in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all the vehicles listed in the database"),
            @ApiResponse(responseCode = "404", description = "Something went wrong, please try again")
    })
    public ResponseEntity<Page<Vehicle>> listAll(@Parameter(hidden = true) Pageable pageable) {
        return ResponseEntity.ok(vehicleService.listAll(pageable));
    }

    @GetMapping(path = "/{id}")
    @Operation(description = "Request a overview of all vehicles that match the given ID in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully found the matching vehicle by ID, from the database"),
            @ApiResponse(responseCode = "404", description = "Something went wrong, please try again")
    })
    public ResponseEntity<Vehicle> findById(@PathVariable int id, @AuthenticationPrincipal UserDetails userDetails) {
        log.info("User logged in {}", userDetails);
        return ResponseEntity.ok(vehicleService.findById(id));
    }

    @GetMapping(path = "/find")
    @Operation(description = "Request a overview of all vehicles that match the given name in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully found the matching vehicle(s) by name, from the database"),
            @ApiResponse(responseCode = "404", description = "Something went wrong, please try again")
    })
    public ResponseEntity<List<Vehicle>> findByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(vehicleService.findByName(name));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Save a vehicle in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully saved the vehicle in the database"),
            @ApiResponse(responseCode = "404", description = "Something went wrong, please try again")
    })
    public ResponseEntity<Vehicle> save(@RequestBody @Valid Vehicle vehicle) {
        return ResponseEntity.ok(vehicleService.save(vehicle));
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Delete a vehicle from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the vehicle from the database"),
            @ApiResponse(responseCode = "404", description = "Something went wrong, please try again")
    })
    public ResponseEntity<Vehicle> delete(@PathVariable int id) {
        vehicleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Update a vehicle in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully updated the vehicle in the database"),
            @ApiResponse(responseCode = "404", description = "Something went wrong, please try again")
    })
    public ResponseEntity<Vehicle> update(@RequestBody Vehicle vehicle) {
        vehicleService.update(vehicle);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
