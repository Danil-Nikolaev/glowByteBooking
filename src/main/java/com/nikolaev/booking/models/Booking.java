package com.nikolaev.booking.models;

import java.time.LocalDate;
import java.util.UUID;

import com.nikolaev.booking.statuses.BookingStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    
    @Id
    private UUID id;
    
    @ManyToOne
    private Loyalty username;
    
    @ManyToOne
    private Hotel hotel;

    private BookingStatus bookingStatus;
    private LocalDate startDate;
    private LocalDate endDate;
}


