package com.buttons.smarthome.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Device {
    @Id
    public UUID id;
    public String name;
    public Type type;
}
