package com.buttons.smarthome.controller;


import com.buttons.smarthome.models.Apartment;
import com.buttons.smarthome.models.Rent;
import com.buttons.smarthome.rent.RentService;
import com.buttons.smarthome.repo.ApartmentRepo;
import com.buttons.smarthome.repo.LandLordRepo;
import com.buttons.smarthome.repo.RenterRepo;
import net.minidev.json.JSONArray;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RenterController {

    private RentService rentService;
    public RenterController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping("/renter/getRent")
    public ResponseEntity<Rent> getMyApartments(long rentID){;
        return new ResponseEntity<>(rentService.getRent(rentID), HttpStatus.ACCEPTED);
    }
}
