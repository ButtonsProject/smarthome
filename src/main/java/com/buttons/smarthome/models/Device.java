package com.buttons.smarthome.models;

import javax.persistence.*;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(columnDefinition = "serial")
    private long id;
    private String name;
    private Type type;

    private String state;

    public Device(String state) {
        this.state = state;
    }

    public Device(String name, String state, Type type) {
        this.name = name;
        this.state = state;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
