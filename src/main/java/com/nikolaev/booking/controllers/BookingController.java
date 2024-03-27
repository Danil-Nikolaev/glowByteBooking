package com.nikolaev.booking.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikolaev.booking.models.Booking;
import com.nikolaev.booking.models.BookingRequest;
import com.nikolaev.booking.models.BookingResponse;
import com.nikolaev.booking.services.BookingResponseService;
import com.nikolaev.booking.services.BookingService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("api/v1/reservations")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingResponseService bookingResponseService;

    @PostMapping
    public ResponseEntity<BookingResponse> saveResarvation(@RequestBody BookingRequest bookingRequest, @RequestHeader("X-User-Name") String username) {
        Booking booking = bookingService.save(bookingRequest, username);
        if (booking == null) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        BookingResponse bookingResponse = bookingResponseService.generateResponse(booking); 
        return ResponseEntity.ok(bookingResponse);
    }

    @DeleteMapping("{id}")
    public void deleteReservation(@PathVariable("id") UUID id, @RequestHeader("X-User-Name") String username) {
        bookingService.delete(id, username);
    }   
}
