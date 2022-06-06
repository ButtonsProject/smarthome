package com.buttons.smarthome.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(columnDefinition = "serial")
    private long id;

    public Apartment(){};

    public Apartment(String name, String address, LandLord landLord, String controlAddress, String authToken) {
        this.name = name;
        this.address = address;
        this.landLord = landLord;
        this.authToken = authToken;
        this.controlAddress = controlAddress;
    }
    private String address;
    private String name;

    private transient String controlAddress;

    private transient String authToken;

    @OneToOne(cascade = CascadeType.ALL)
    private LandLord landLord;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Device> devices;


    public long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getControlAddress() {
        return controlAddress;
    }

    public void setControlAddress(String controlAddress) {
        this.controlAddress = controlAddress;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
