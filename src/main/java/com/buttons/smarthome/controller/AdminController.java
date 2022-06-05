package com.buttons.smarthome.controller;

import com.buttons.smarthome.models.Apartment;
import com.buttons.smarthome.models.LandLord;
import com.buttons.smarthome.models.Renter;
import com.buttons.smarthome.repo.ApartmentRepo;
import com.buttons.smarthome.repo.LandLordRepo;
import com.buttons.smarthome.repo.RenterRepo;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import net.minidev.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class AdminController {

    private final LandLordRepo landLordRepo;
    private final ApartmentRepo apartmentRepo;
    private final RenterRepo renterRepo;

    public AdminController(LandLordRepo landLordRepo, ApartmentRepo apartmentRepo, RenterRepo renterRepo) {
        this.landLordRepo = landLordRepo;
        this.apartmentRepo = apartmentRepo;
        this.renterRepo = renterRepo;
    }

    @PostMapping("/admin/addLandLord")
    public ResponseEntity<String> addLandLord(@RequestBody String name, @RequestBody String surname){
        landLordRepo.save(new LandLord(name, surname));
        return new ResponseEntity<String>("SAVE", HttpStatus.ACCEPTED);
    }


    @GetMapping("/admin/getLandLordList")
    public ResponseEntity<String> getLandLords(){
        var landLord = landLordRepo.findAll();
        var json = new JSONArray();
        for (var l: landLord) {
            json.add(l);
        }
        return new ResponseEntity<>(json.toJSONString(), HttpStatus.ACCEPTED);
    }

    //TODO: Доделать - почему-то прокидывает поле рентеров
    @PostMapping("/admin/addApartment")
    public ResponseEntity<String> addApartment(@RequestBody long landLordID, @RequestBody Apartment apartment) {
        var landLord = landLordRepo.findById(landLordID).get();
        apartmentRepo.save(new Apartment(apartment.getName(), apartment.getAddress(), landLord));
        return new ResponseEntity<String>("SAVE", HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin/getApartmentList")
    public ResponseEntity<String> getApartmentList(){
        var apartments = apartmentRepo.findAll();
        var json = new JSONArray();
        json.add(apartments);
        return new ResponseEntity<>(json.toJSONString(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/admin/addRenter")
    public ResponseEntity<String> addRenter(@RequestBody Renter renter) {
        renterRepo.save(renter);
        return new ResponseEntity<String>("SAVE", HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin/getRenters")
    public ResponseEntity<String> getRenters(){
        var renters = renterRepo.findAll();
        var json = new JSONArray();
        json.add(renters);
        return new ResponseEntity<>(json.toJSONString(), HttpStatus.ACCEPTED);
    }

}
