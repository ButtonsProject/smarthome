package com.buttons.smarthome.models;

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

    public String name;
    public String surname;

}
