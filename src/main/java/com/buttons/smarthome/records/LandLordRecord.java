package com.buttons.smarthome.records;

import io.swagger.v3.oas.annotations.media.Schema;

public class LandLordRecord {
    private String name;

    private String surname;

    public LandLordRecord(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
