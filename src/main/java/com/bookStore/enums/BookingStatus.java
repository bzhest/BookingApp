package com.bookStore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BookingStatus {
    SUBMITTED,
    REJECTED,
    APPROVED,
    CANCELED,
    IN_DELIVERY,
    COMPLETED
}
