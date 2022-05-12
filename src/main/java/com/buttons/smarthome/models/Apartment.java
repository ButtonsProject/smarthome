package com.buttons.smarthome.models;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(columnDefinition = "serial")
    public long id;

    public Apartment(){};

    public Apartment(String name, String address, LandLord landLord) {
        this.name = name;
        this.address = address;
        this.landLord = landLord;
    }

    public String address;
    public String name;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Renter> renters;

    @OneToOne(cascade = CascadeType.ALL)
    public LandLord landLord;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Device> Devices;
}
