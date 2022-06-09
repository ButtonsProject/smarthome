package com.buttons.smarthome.controller;


import com.buttons.smarthome.services.deviceControl.CommandEndpointRecord;
import com.buttons.smarthome.services.deviceControl.DeviceService;
import com.buttons.smarthome.models.Device;
import com.buttons.smarthome.models.Rent;
import com.buttons.smarthome.records.RentEndpointRecord;
import com.buttons.smarthome.services.RentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.LinkedList;
import java.util.List;

@RestController
public class RenterController {

    private RentService rentService;

    private DeviceService deviceService;
    public RenterController(RentService rentService, DeviceService deviceService) {

        this.rentService = rentService;
        this.deviceService = deviceService;
    }

    @GetMapping("/renter/getRents")
    public ResponseEntity<List<RentEndpointRecord>> getMyApartments(long renterID){
        var rents = rentService.getRents();
        var list = new LinkedList<RentEndpointRecord>();
        rents.forEach(rent -> list.add(rent.getRentInfo()));
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @GetMapping("/renter/getRent")
    public ResponseEntity<Rent> getApartment(long rentID){
        return new ResponseEntity<>(rentService.getRent(rentID), HttpStatus.ACCEPTED);
    }

    @GetMapping("/renter/getDevices")
    public ResponseEntity<List<Device>> getDevices(long rentID){
        var devices = rentService.getRent(rentID).getApartment().getDevices();
        return new ResponseEntity<>(devices, HttpStatus.ACCEPTED);
    }

    @PostMapping("/renter/sendCommand")
    public ResponseEntity<String> sendCommand(@RequestBody CommandEndpointRecord command) throws IOException, InterruptedException {
        var deviceId = command.deviceId;
        var rent = command.rentId;
        var apartment = rentService.getRent(rent).getApartment();
        var device = apartment
                .getDevices()
                .stream()
                .filter(dev -> dev.getId() == deviceId)
                .findAny()
                .orElse(null);


        assert device != null;
        deviceService.sendToDevice(apartment, device, command.command);
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }
}
