package com.nikolaev.booking.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.nikolaev.booking.models.Booking;
import com.nikolaev.booking.models.BookingResponse;
import com.nikolaev.booking.models.Loyalty;

@Service
public class BookingResponseService {
    public BookingResponse generateResponse(Booking booking) {
        LocalDate starDate = booking.getStartDate();
        LocalDate endDate = booking.getEndDate();
        int countNight =  calculationCountDay(starDate, endDate);
        int price = booking.getHotel().getPrice();
        int sum = calculationSum(countNight, price, booking.getUsername());
        return new BookingResponse(booking, countNight, sum);
    }

    private int calculationCountDay(LocalDate startDate, LocalDate enDate) {
        return (int) startDate.until(enDate, ChronoUnit.DAYS);
    }

    private int calculationSum(int countNight, int price, Loyalty loyalty) {
        return (int) ((countNight * price) * (1 - LoyaltyDiscounts.getDiscount(loyalty.getLoyaltyStatus())));
    }
}
