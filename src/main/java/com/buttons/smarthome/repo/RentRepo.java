package com.buttons.smarthome.repo;

import com.buttons.smarthome.models.Rent;
import com.buttons.smarthome.models.Renter;
import org.springframework.data.repository.CrudRepository;

public interface RentRepo extends CrudRepository<Rent, Long> {
}