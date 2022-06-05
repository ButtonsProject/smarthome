package com.buttons.smarthome.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private long id;

    public Rent(){}

    public Rent(Apartment apartment, Renter renter, LocalDateTime startTimeRent, LocalDateTime endTimeRent, String description) {
        this.apartment = apartment;
        this.renter = renter;
        this.startTimeRent = startTimeRent;
        this.endTimeRent = endTimeRent;
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    public Apartment apartment;
    @ManyToOne
    @JoinColumn(name = "renter_id")
    public Renter renter;
    public LocalDateTime startTimeRent;
    public LocalDateTime endTimeRent;
    public String description;

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public long getId() {
        return id;
    }
}
