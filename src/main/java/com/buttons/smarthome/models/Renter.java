package com.buttons.smarthome.models;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Renter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private long id;

    public Renter(){}

    public Renter(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Schema(description =  "Pavlusha")
    public String name;

    @Schema(description =  "Livov")
    public String surname;

    public long getId() {
        return id;
    }
}
