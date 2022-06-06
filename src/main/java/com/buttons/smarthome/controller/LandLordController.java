package com.buttons.smarthome.controller;

import com.buttons.smarthome.services.deviceControl.CommandEndpointRecord;
import com.buttons.smarthome.models.Device;
import com.buttons.smarthome.services.RentService;
import com.buttons.smarthome.repo.ApartmentRepo;
import com.buttons.smarthome.repo.LandLordRepo;
import net.minidev.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LandLordController {
    private final LandLordRepo landLordRepo;
    private final ApartmentRepo apartmentRepo;
    private final RenterController renterRepo;
    private RentService rentService;

    public LandLordController(LandLordRepo landLordRepo, ApartmentRepo apartmentRepo,
                              RenterController renterController, RentService rentService){
        this.landLordRepo = landLordRepo;
        this.apartmentRepo = apartmentRepo;
        this.renterRepo = renterController;
        this.rentService = rentService;
    }

    //TODO: Переписать по-человечески
    @GetMapping("/landLord/getApartmentList")
    public ResponseEntity<String> getApartments(@RequestBody long landLordId){
        var apartments = landLordRepo.findById(landLordId).get().getApartments();
        var json = new JSONArray();
        json.add(apartments);
        return new ResponseEntity<>(json.toJSONString(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/landLord/getDevices")
    public ResponseEntity<List<Device>> getDevices(@RequestBody long apartmentID){
        var devices = apartmentRepo.findById(apartmentID).get().getDevices();
        return new ResponseEntity<>(devices, HttpStatus.ACCEPTED);
    }

    @PostMapping("/landLord/sendCommand")
    public ResponseEntity<String> sendCommand(@RequestBody CommandEndpointRecord command){
        return new ResponseEntity<>("device", HttpStatus.ACCEPTED);
    }

    //TODO: Переписать по-человечески
//    @GetMapping("/landLord/getRenters")
//    public ResponseEntity<String> getRenters(@RequestBody long apartmentID){
//        var renters = apartmentRepo.findById(apartmentID).get().getRenters();
//        var json = new JSONArray();
//        json.add(renters);
//        return new ResponseEntity<>("SAVE", HttpStatus.ACCEPTED);
//    }
}
