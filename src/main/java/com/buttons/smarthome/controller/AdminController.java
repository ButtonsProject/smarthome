package com.buttons.smarthome.controller;

import com.buttons.smarthome.records.ApartmentEndpointRecord;
import com.buttons.smarthome.models.*;
import com.buttons.smarthome.records.*;
import com.buttons.smarthome.records.RentEndpointRecord;
import com.buttons.smarthome.services.RentService;
import com.buttons.smarthome.repo.ApartmentRepo;
import com.buttons.smarthome.repo.LandLordRepo;
import com.buttons.smarthome.repo.RentRepo;
import com.buttons.smarthome.repo.RenterRepo;
import net.minidev.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


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
    public ResponseEntity<String> addLandLord(@RequestBody LandLordRecord record){
        landLordRepo.save(new LandLord(record.getName(), record.getSurname()));
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
    public ResponseEntity<String> addApartment(@RequestBody ApartmentEndpointRecord record) {
        var landLord = landLordRepo.findById(record.getLandLordId()).get();
        var apartments = new Apartment(record.getName(), record.getAddress(), landLord,
                record.getControlAddress(), record.getAuthKey());
        landLord.getApartments().add(apartments);
        apartmentRepo.save(apartments);
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
    public ResponseEntity<String> addRenter(@RequestBody RenterRecord record) {
        renterRepo.save(new Renter(record.getName(), record.getSurname()));
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
    public ResponseEntity<HashMap<String, Long>> addDevice(@RequestBody DeviceRecord record) {
        var apartment = apartmentRepo.findById(record.getApartmentId()).get();
        var device = new Device(record.getName(), record.getState(), record.getType());
        apartment.getDevices().add(device);
        apartmentRepo.save(apartment);
        var response = new HashMap<String, Long>();
        response.put("id", device.getId());
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
