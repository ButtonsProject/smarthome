package com.buttons.smarthome.records;

import java.time.LocalDateTime;

public class RentEndpointRecord {

    public RentEndpointRecord(long apartmentId, long renterId, LocalDateTime startRentTime, LocalDateTime endRentTime, String description){
        this.apartmentId = apartmentId;
        this.endRentTime = endRentTime;
        this.renterId = renterId;
        this.startRentTime = startRentTime;
        this.description = description;
    }

    public long apartmentId;
    public long renterId;
    public LocalDateTime startRentTime;
    public LocalDateTime endRentTime;
    public String description;

}
