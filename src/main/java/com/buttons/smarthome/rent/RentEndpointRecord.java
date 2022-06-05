package com.buttons.smarthome.rent;

import java.time.LocalDateTime;

public class RentEndpointRecord {

    public long apartmentId;
    public long renterId;
    public LocalDateTime startRentTime;
    public LocalDateTime endRentTime;
    public String description;

}
