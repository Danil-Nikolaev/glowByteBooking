package com.nikolaev.booking.services;
import java.util.HashMap;
import java.util.Map;

import com.nikolaev.booking.statuses.LoyaltyStatus;

public class LoyaltyDiscounts {
    private static final Map<LoyaltyStatus, Double> discounts = new HashMap<>();

    static {
        discounts.put(LoyaltyStatus.BRONZE, 0.05); // 5% скидка
        discounts.put(LoyaltyStatus.SILVER, 0.07); // 7% скидка
        discounts.put(LoyaltyStatus.GOLD, 0.10); // 10% скидка
    }

    public static double getDiscount(LoyaltyStatus status) {
        return discounts.getOrDefault(status, 0.0); // Если статус не найден, вернуть 0
    }
}
