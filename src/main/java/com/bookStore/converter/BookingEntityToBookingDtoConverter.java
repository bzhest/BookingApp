package com.bookStore.converter;

import com.bookStore.dto.BookingDto;
import com.bookStore.entity.Booking;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
            dto.setBookingStatusName(booking.getStatus().getName());
            dto.setDate(booking.getDate());
            dto.setTime(setLocalTime(booking));
            dto.setPrice(booking.getPrice());
            dto.setProductTitle(booking.getProduct().getName());
            return dto;
        }
        return null;
    }

    private String setLocalTime(Booking booking){
        LocalTime localTime = booking.getTime().toLocalTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return formatter.format(localTime);
    }
}
