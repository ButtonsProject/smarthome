package com.buttons.smarthome.rent;

import com.buttons.smarthome.models.Apartment;
import com.buttons.smarthome.models.Rent;
import com.buttons.smarthome.repo.ApartmentRepo;
import com.buttons.smarthome.repo.LandLordRepo;
import com.buttons.smarthome.repo.RentRepo;
import com.buttons.smarthome.repo.RenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentService {

    private final LandLordRepo landLordRepo;
    private final ApartmentRepo apartmentRepo;
    private final RenterRepo renterRepo;

    private final RentRepo rentRepo;

    public RentService(LandLordRepo landLordRepo, ApartmentRepo apartmentRepo, RenterRepo renterRepo, RentRepo rentRepo) {
        this.landLordRepo = landLordRepo;
        this.apartmentRepo = apartmentRepo;
        this.renterRepo = renterRepo;
        this.rentRepo = rentRepo;
    }


    public Long createRent(RentEndpointRecord record) {
        var apartment = apartmentRepo.findById(record.apartmentId).get();
        var renter = renterRepo.findById(record.renterId).get();
        var rent = new Rent(apartment, renter, record.startRentTime, record.endRentTime, record.description);
        rentRepo.save(rent);
        return rent.getId();
    }

    public Rent getRent(long rentID) {
        return rentRepo.findById(rentID).get();
    }

}
