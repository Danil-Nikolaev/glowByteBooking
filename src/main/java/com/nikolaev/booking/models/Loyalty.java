package com.nikolaev.booking.models;

import com.nikolaev.booking.statuses.LoyaltyStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loyalty {

    @Id
    private String username;
    private int countOfBookings;
    private LoyaltyStatus loyaltyStatus; 
}
