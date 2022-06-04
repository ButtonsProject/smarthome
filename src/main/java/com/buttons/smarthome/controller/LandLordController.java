package com.buttons.smarthome.controller;

import com.buttons.smarthome.models.Apartment;
import com.buttons.smarthome.models.LandLord;
import com.buttons.smarthome.repo.ApartmentRepo;
import com.buttons.smarthome.repo.LandLordRepo;
import com.fasterxml.jackson.databind.JsonNode;
import net.minidev.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LandLordController {
    private final LandLordRepo landLordRepo;
    private final ApartmentRepo apartmentRepo;
    private final RenterController renterRepo;

    public LandLordController(LandLordRepo landLordRepo, ApartmentRepo apartmentRepo, RenterController renterController){
        this.landLordRepo = landLordRepo;
        this.apartmentRepo = apartmentRepo;
        this.renterRepo = renterController;
    }

    //TODO: Переписать по-человечески
    @GetMapping("/landLord/getApartmentList")
    public ResponseEntity<String> getApartments(@RequestBody long landLordId){
        var apartments = landLordRepo.findById(landLordId).get().getApartments();
        var json = new JSONArray();
        json.add(apartments);
        return new ResponseEntity<>(json.toJSONString(), HttpStatus.ACCEPTED);
    }

    //TODO: Написать реализацию добавления апартаментов текущему лэндлорду
    @PostMapping("/landLord/addApartment")
    public ResponseEntity<String> addApartment(@RequestBody Apartment apartment){
        apartmentRepo.save(apartment);
        return new ResponseEntity<>("SAVE", HttpStatus.ACCEPTED);
    }

    //TODO: Переписать по-человечески
    @GetMapping("/landLord/getRenters")
    public ResponseEntity<String> getRenters(@RequestBody long apartmentID){
        var renters = apartmentRepo.findById(apartmentID).get().getRenters();
        var json = new JSONArray();
        json.add(renters);
        return new ResponseEntity<>("SAVE", HttpStatus.ACCEPTED);
    }
}
