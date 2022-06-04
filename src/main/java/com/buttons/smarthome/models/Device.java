package com.buttons.smarthome.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Device {
    @Id
    private long id;
    private long name;
    private Type type;

    public long getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public long getId() {
        return id;
    }
}
