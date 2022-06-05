package com.buttons.smarthome.models;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(columnDefinition = "serial")
    private long id;

    public Apartment(){};

    public Apartment(String name, String address, LandLord landLord) {
        this.name = name;
        this.address = address;
        this.landLord = landLord;
    }

    private String address;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Renter> renters;

    @OneToOne(cascade = CascadeType.ALL)
    private LandLord landLord;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Device> Devices;

    public List<Renter> getRenters() {
        return renters;
    }

    public long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }
}
