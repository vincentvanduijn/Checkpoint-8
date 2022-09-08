package com.devoteam.VehicleApplication.controller;

import com.devoteam.VehicleApplication.domain.Automaker;
import com.devoteam.VehicleApplication.domain.Vehicle;
import com.devoteam.VehicleApplication.domain.VehicleType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("vehicle")
public class VehicleController {

    //localhost:8080/vehicle/list

//    @Autowired
//    private DateUtil dateUtil;

    @GetMapping(path = "/list")
    public List<Vehicle> listAll() {
        return List.of(new Vehicle(1, new Date(), Automaker.builder().build(), "Malibu", "Red", 2000, VehicleType.builder().build()));
    }
}
