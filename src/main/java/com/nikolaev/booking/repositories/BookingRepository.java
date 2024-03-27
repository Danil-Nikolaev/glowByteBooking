package com.nikolaev.booking.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nikolaev.booking.models.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, UUID> {
    
}
