package com.bookStore.service;

import com.bookStore.dto.BookingDto;
import com.bookStore.entity.Booking;
import com.bookStore.entity.BookingStatus;
import com.bookStore.entity.Product;
import com.bookStore.entity.User;
import com.bookStore.repository.BookingRepository;
import com.bookStore.repository.BookingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingStatusRepository bookingStatusRepository;


    public Booking createBooking(BookingDto bookingDto) {
        Product productById = productService.getProductById(bookingDto.getProductId());
        User userById = userService.getUserById(bookingDto.getUserId());
        BookingStatus bookingStatusById = getBookingStatusById(bookingDto.getBookingStatusId());

        Booking booking = new Booking();
        booking.setProduct(productById);
        booking.setUser(userById);
        booking.setDeliveryAddress(bookingDto.getDeliveryAddress());
        booking.setDate(Date.valueOf(LocalDate.now()));
        booking.setTime(Time.valueOf(LocalTime.now()));
        booking.setStatus(bookingStatusById);
        booking.setQuantity(bookingDto.getQuantity());
        return bookingRepository.save(booking);
    }

    public BookingStatus getBookingStatusById(Integer id) {
        return bookingStatusRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    public String getBookingStatus(Integer id) {
        return bookingStatusRepository.findById(id).get().getName();
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Integer id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Booking updateBookingStatus(Integer id, BookingStatus status) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setStatus(status);
            return bookingRepository.save(booking);
        }
        return null;
    }

    public void deleteBooking(Integer id) {
        bookingRepository.deleteById(id);
    }
}
