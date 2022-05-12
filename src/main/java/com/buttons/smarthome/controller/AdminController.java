package com.buttons.smarthome.controller;

import com.buttons.smarthome.models.Apartment;
import com.buttons.smarthome.models.LandLord;
import com.buttons.smarthome.repo.ApartmentRepo;
import com.buttons.smarthome.repo.LandLordRepo;
import com.fasterxml.jackson.databind.JsonNode;
import net.minidev.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminController {

    private final LandLordRepo landLordRepo;
    private final ApartmentRepo apartmentRepo;

    public AdminController(LandLordRepo landLordRepo, ApartmentRepo apartmentRepo) {
        this.landLordRepo = landLordRepo;
        this.apartmentRepo = apartmentRepo;
    }

    @PostMapping("/admin/addLandLord")
    public ResponseEntity<String> addLandLord(@RequestBody JsonNode payload){
        landLordRepo.save(new LandLord(payload.findValue("name").textValue(),payload.findValue("surname").textValue()));
        return new ResponseEntity<String>("SAVE", HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin/getLandLordList")
    public ResponseEntity<String> addLandLord(){
        var landLord = landLordRepo.findAll();
        var json = new JSONArray();
        for (var l: landLord) {
            json.add(l);
        }
        return new ResponseEntity<>(json.toJSONString(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/admin/addApartment")
    public ResponseEntity<String> addApartment(@RequestBody JsonNode payload){
        var name = payload.findValue("name").toString();
        var address = payload.findValue("address").toString();
        var landLordId = payload.findValue("landLordId").asLong();
        var landLord = landLordRepo.findById(landLordId).get();
        apartmentRepo.save(new Apartment(name, address, landLord));
        return new ResponseEntity<String>("SAVE", HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin/getApartmentList")
    public ResponseEntity<String> getApartmentList(){
        var apartments = apartmentRepo.findAll();
        var json = new JSONArray();
        json.add(apartments);
        return new ResponseEntity<>(json.toJSONString(), HttpStatus.ACCEPTED);
    }

}
