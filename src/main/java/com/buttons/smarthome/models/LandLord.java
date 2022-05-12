package com.buttons.smarthome.models;

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
    public long id;
    public String name;
    public String surname;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Apartment> apartments;
}
