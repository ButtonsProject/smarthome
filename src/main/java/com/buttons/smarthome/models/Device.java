package com.buttons.smarthome.models;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(columnDefinition = "serial")
    private long id;
    private String name;
    private Type type;

    public Device(String name, Type type){
        this.name = name;
        this.type = type;
    }

    public Device() {

    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public long getId() {
        return id;
    }
}
