package com.buttons.smarthome.records;

import com.buttons.smarthome.models.Type;

public class DeviceRecord {

    private long id;
    private String name;
    private Type type;
    private long apartmentId;

    public DeviceRecord(long id, String name, Type type, long apartmentId){
        this.id = id;
        this.name = name;
        this.type = type;
        this.apartmentId = apartmentId;
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

    public long getApartmentId() {
        return apartmentId;
    }
}
