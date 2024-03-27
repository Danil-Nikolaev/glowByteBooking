package com.nikolaev.booking.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nikolaev.booking.models.Loyalty;

@Repository
public interface LoyaltyRepository extends CrudRepository<Loyalty, String> {
    
}
