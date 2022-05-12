package com.buttons.smarthome.repo;

import com.buttons.smarthome.models.Apartment;
import org.springframework.data.repository.CrudRepository;

public interface ApartmentRepo extends CrudRepository<Apartment, Integer> {
}
