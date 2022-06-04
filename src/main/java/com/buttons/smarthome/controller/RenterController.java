package com.buttons.smarthome.controller;


import com.buttons.smarthome.models.Apartment;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RenterController {

    private final LandLordRepo landLordRepo;
    private final ApartmentRepo apartmentRepo;
    private final RenterRepo renterRepo;

    public RenterController(LandLordRepo landLordRepo, ApartmentRepo apartmentRepo, RenterRepo renterRepo) {
        this.landLordRepo = landLordRepo;
        this.apartmentRepo = apartmentRepo;
        this.renterRepo = renterRepo;
    }

    @GetMapping("/renter/getMyApartments")
    public ResponseEntity<String> getMyApartments(){
        return new ResponseEntity<>("template", HttpStatus.ACCEPTED);
    }
}
