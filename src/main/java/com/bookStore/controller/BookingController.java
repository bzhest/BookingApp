package com.bookStore.controller;

import com.bookStore.dto.BookingDto;
import com.bookStore.entity.Booking;
import com.bookStore.entity.BookingStatus;
import com.bookStore.service.BookingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    @PreAuthorize("hasAnyRole('MANAGER', 'CUSTOMER')")
    public BookingDto createBooking(@RequestBody BookingDto bookingDto) {
        return bookingService.createBooking(bookingDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('MANAGER', 'CUSTOMER')")
    public List<BookingDto> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'CUSTOMER')")
    public BookingDto getBookingById(@PathVariable Integer id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping(value = "/{id}/status", produces = "application/json")
    @PreAuthorize("hasAnyRole('MANAGER', 'CUSTOMER')")
    public ResponseEntity<?> getBookingStatus(@PathVariable Integer id) {
        String status = bookingService.getBookingStatus(id);
        return ResponseEntity.ok("{\"status\":\"" + StringUtils.capitalize(status) + "\"}");
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('MANAGER', 'CUSTOMER')")
    public BookingDto updateBookingStatus(@PathVariable Integer id, @RequestBody BookingStatus status) {
        return bookingService.updateBookingStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'CUSTOMER')")
    public void deleteBooking(@PathVariable Integer id) {
        bookingService.deleteBooking(id);
    }

}
