package com.buttons.smarthome.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
public class Renter {
    @Id
    public UUID id;
    public String name;
    public String surname;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Apartment> apartments;
}
