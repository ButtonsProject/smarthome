package com.buttons.smarthome.records;

public class ApartmentEndpointRecord {
    private String name;
    private String address;
    private String imgUrl;
    private long landLordId;
    private String controlAddress;
    private String authKey;

    public ApartmentEndpointRecord(long landLordId, String name, String address, String imgUrl, String controlAddress, String authKey){
        this.address = address;
        this.landLordId = landLordId;
        this.name = name;
        this.imgUrl = imgUrl;
        this.controlAddress = controlAddress;
        this.authKey = authKey;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public long getLandLordId() {
        return landLordId;
    }

    public String getControlAddress() {
        return controlAddress;
    }

    public String getAuthKey() {
        return authKey;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
