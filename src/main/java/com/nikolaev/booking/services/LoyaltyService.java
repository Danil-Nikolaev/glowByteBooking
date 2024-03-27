package com.nikolaev.booking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikolaev.booking.models.Loyalty;
import com.nikolaev.booking.repositories.LoyaltyRepository;
import com.nikolaev.booking.statuses.LoyaltyStatus;

@Service
public class LoyaltyService {
    
    @Autowired
    private LoyaltyRepository loyaltyRepository;

    public Loyalty findByUsername(String username) {
        return loyaltyRepository.findById(username).orElse(null);
    }

    public Loyalty save(String username) {
        Loyalty loyalty = new Loyalty();
        loyalty.setUsername(username);
        loyalty.setLoyaltyStatus(LoyaltyStatus.BRONZE);
        loyalty.setCountOfBookings(0);
        return loyaltyRepository.save(loyalty);
    }

    public Loyalty incrementCountOfBookings(String username) {
        Loyalty loyalty = findByUsername(username);
        loyalty.setCountOfBookings(loyalty.getCountOfBookings() + 1);

        if (loyalty.getCountOfBookings() > 10) 
            loyalty.setLoyaltyStatus(LoyaltyStatus.SILVER);
        else if (loyalty.getCountOfBookings() > 20)
            loyalty.setLoyaltyStatus(LoyaltyStatus.GOLD);
        
        return loyaltyRepository.save(loyalty);
    }

    public Loyalty decrementCountOfBooks(String username) {

        Loyalty loyalty = findByUsername(username);
        loyalty.setCountOfBookings(loyalty.getCountOfBookings() - 1);

        if (loyalty.getCountOfBookings() <= 10) 
            loyalty.setLoyaltyStatus(LoyaltyStatus.BRONZE);
        else if (loyalty.getCountOfBookings() <= 20)
            loyalty.setLoyaltyStatus(LoyaltyStatus.SILVER);
        
        return loyaltyRepository.save(loyalty);
       
    }
}
