package com.bookStore.converter;

import com.bookStore.dto.BookingDto;
import com.bookStore.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingEntityToBookingDtoConverter {

    public BookingDto convert(Booking booking) {
        if (null != booking) {
            BookingDto dto = new BookingDto();
            dto.setId(booking.getId());
            dto.setProductId(booking.getProduct().getId());
            dto.setUserId(booking.getUser().getId());
            dto.setQuantity(booking.getQuantity());
            dto.setDeliveryAddress(booking.getDeliveryAddress());
            dto.setBookingStatusId(booking.getStatus().getId());
            return dto;
        }
        return null;
    }
}
