package com.buttons.smarthome.models;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
public class LandLord implements Serializable {

    public LandLord(){}

    public LandLord(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(columnDefinition = "serial")
    private long id;

    @Schema(description =  "Pavlusha")
    public String name;

    @Schema(description =  "Livov")
    public String surname;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Apartment> apartments;

    public long getId() {return id;}

    public List<Apartment> getApartments(){
        return apartments;
    }
}
