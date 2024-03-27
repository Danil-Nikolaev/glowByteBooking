package com.nikolaev.booking.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikolaev.booking.models.Booking;
import com.nikolaev.booking.models.BookingRequest;
import com.nikolaev.booking.repositories.BookingRepository;
import com.nikolaev.booking.statuses.BookingStatus;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private LoyaltyService loyaltyService;
    
    private boolean check(BookingRequest bookingRequest, String username) {
        if (hotelService.findById(bookingRequest.getHotelUid()) == null)
            return false;

        if (bookingRequest.getStartDate().isAfter(bookingRequest.getEndDate()))
            return false;
        
        if (loyaltyService.findByUsername(username) == null) 
            loyaltyService.save(username);

        return true;
    }

    public Booking save(BookingRequest bookingRequest, String username) {
        if (!check(bookingRequest, username))
            return null;

        Booking booking = new Booking();
        booking.setId(UUID.randomUUID());
        booking.setStartDate(bookingRequest.getStartDate());
        booking.setEndDate(bookingRequest.getEndDate());
        booking.setUsername(loyaltyService.findByUsername(username));
        booking.setBookingStatus(BookingStatus.SUCCESS);
        booking.setHotel(hotelService.findById(bookingRequest.getHotelUid()));
        bookingRepository.save(booking);
        booking.setUsername(loyaltyService.incrementCountOfBookings(username));
        return bookingRepository.save(booking);
    }

    public void delete(UUID id, String username) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking == null) return;

        booking.setBookingStatus(BookingStatus.CANCELED);
        booking.setUsername(loyaltyService.decrementCountOfBooks(username));
        bookingRepository.save(booking);
    }
}
