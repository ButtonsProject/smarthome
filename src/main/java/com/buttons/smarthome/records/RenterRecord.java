package com.buttons.smarthome.records;

public class RenterRecord {
    private String name;

    private String surname;

    public RenterRecord(String name, String surname){
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
