package com.nikolaev.booking.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nikolaev.booking.models.Hotel;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, UUID> {
    
}
