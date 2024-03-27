package com.nikolaev.booking.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikolaev.booking.models.Hotel;
import com.nikolaev.booking.repositories.HotelRepository;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public Hotel findById(UUID id) {
        return hotelRepository.findById(id).orElse(null);
    }
}
