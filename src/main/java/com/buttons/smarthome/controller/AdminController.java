package com.buttons.smarthome.controller;

import com.buttons.smarthome.models.*;
import com.buttons.smarthome.rent.RentEndpointRecord;
import com.buttons.smarthome.rent.RentService;
import com.buttons.smarthome.repo.ApartmentRepo;
import com.buttons.smarthome.repo.LandLordRepo;
import com.buttons.smarthome.repo.RentRepo;
import com.buttons.smarthome.repo.RenterRepo;
import com.fasterxml.jackson.databind.JsonNode;
import net.minidev.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class AdminController {

    private final RentService rentService;
    private final LandLordRepo landLordRepo;
    private final ApartmentRepo apartmentRepo;
    private final RenterRepo renterRepo;

    private final RentRepo rentRepo;

    public AdminController(RentService rentService, LandLordRepo landLordRepo, ApartmentRepo apartmentRepo, RenterRepo renterRepo, RentRepo rentRepo) {
        this.rentService = rentService;
        this.landLordRepo = landLordRepo;
        this.apartmentRepo = apartmentRepo;
        this.renterRepo = renterRepo;
        this.rentRepo = rentRepo;
    }

    @PostMapping("/admin/addLandLord")
    public ResponseEntity<String> addLandLord(@RequestBody String name, String surname){
        landLordRepo.save(new LandLord(name, surname));
        return new ResponseEntity<String>("SAVE", HttpStatus.ACCEPTED);
    }


    @GetMapping("/admin/getLandLordList")
    public ResponseEntity<String> getLandLord(){
        var landLord = landLordRepo.findAll();
        var json = new JSONArray();
        for (var l: landLord) {
            json.add(l);
        }
        return new ResponseEntity<>(json.toJSONString(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/admin/addApartment")
    public ResponseEntity<String> addApartment(@RequestBody JsonNode payload) {
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

    @PostMapping("/admin/addRenter")
    public ResponseEntity<String> addRenter(@RequestBody String name, String surname) {
        renterRepo.save(new Renter(name, surname));
        return new ResponseEntity<String>("SAVE", HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin/getRenters")
    public ResponseEntity<String> getRenters(){
        var renters = renterRepo.findAll();
        var json = new JSONArray();
        json.add(renters);
        return new ResponseEntity<>(json.toJSONString(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/admin/addRent")
    public ResponseEntity<HashMap<String, Long>> addRent(@RequestBody RentEndpointRecord record) {
        var id = rentService.createRent(record);
        var response = new HashMap<String, Long>();
        response.put("id", id);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/admin/addDevice")
    public ResponseEntity<HashMap<String, Long>> addDevice(@RequestBody Device device, long apartmentId) {
        var apartment = apartmentRepo.findById(apartmentId).get();
        apartment.getDevices().add(device);
        apartmentRepo.save(apartment);
        var response = new HashMap<String, Long>();
        response.put("id", device.getId());
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
