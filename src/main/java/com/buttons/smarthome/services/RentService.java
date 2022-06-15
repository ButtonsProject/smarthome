package com.buttons.smarthome.services;

import com.buttons.smarthome.models.Rent;
import com.buttons.smarthome.records.RentEndpointRecord;
import com.buttons.smarthome.repo.ApartmentRepo;
import com.buttons.smarthome.repo.LandLordRepo;
import com.buttons.smarthome.repo.RentRepo;
import com.buttons.smarthome.repo.RenterRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;


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
        var apartment = apartmentRepo.findById(record.apartmentId);
        var renter = renterRepo.findById(record.renterId);
        if (apartment.isPresent() && renter.isPresent()) {
            var rent = new Rent(apartment.get(), renter.get(), record.startRentTime, record.endRentTime, record.description);
            rentRepo.save(rent);
            return rent.getId();
        }
        else
            throw new IllegalArgumentException("Can't find value");
    }

    public List<Rent> getRenterRents(long RenterID) {
        return StreamSupport.stream(rentRepo.findAll().spliterator(), true)
                .filter(r -> r.getRenter().getId() == RenterID).toList();
    }

    public Rent getRent(long rentID) {
        var rents = rentRepo.findById(rentID);
        if (rents.isPresent()) {
            return rents.get();
        }
        else
            throw new IllegalArgumentException();
    }
    public Iterable<Rent> getRents(){
        return  rentRepo.findAll();
    }

}
