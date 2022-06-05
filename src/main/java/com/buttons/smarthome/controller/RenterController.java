package com.buttons.smarthome.controller;


import com.buttons.smarthome.deviceControl.CommandEndpointRecord;
import com.buttons.smarthome.models.Device;
import com.buttons.smarthome.models.Rent;
import com.buttons.smarthome.rent.RentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RenterController {

    private RentService rentService;
    public RenterController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping("/renter/getRent")
    public ResponseEntity<Rent> getMyApartments(long rentID){
        return new ResponseEntity<>(rentService.getRent(rentID), HttpStatus.ACCEPTED);
    }

    @GetMapping("/renter/getDevices")
    public ResponseEntity<List<Device>> getDevices(long rentID){
        var devices = rentService.getRent(rentID).getApartment().getDevices();
        return new ResponseEntity<>(devices, HttpStatus.ACCEPTED);
    }

    @PostMapping("/renter/sendCommand")
    public ResponseEntity<String> sendCommand(CommandEndpointRecord command){
        return new ResponseEntity<>("device", HttpStatus.ACCEPTED);
    }
}
