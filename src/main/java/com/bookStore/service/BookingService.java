package com.bookStore.service;

import com.bookStore.converter.BookingEntityToBookingDtoConverter;
import com.bookStore.dto.BookingDto;
import com.bookStore.entity.Booking;
import com.bookStore.entity.BookingStatus;
import com.bookStore.entity.Product;
import com.bookStore.entity.User;
import com.bookStore.repository.BookingRepository;
import com.bookStore.repository.BookingStatusRepository;
import com.bookStore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ProductRepository productRepository;
    private final BookingEntityToBookingDtoConverter bookingEntityToBookingDtoConverter;

    private final UserService userService;

    private final BookingStatusRepository bookingStatusRepository;


    public BookingDto createBooking(BookingDto bookingDto) {
        Product productById = productRepository.findById(bookingDto.getProductId()).orElse(null);
        User userById = userService.getUserById(bookingDto.getUserId());
        BookingStatus bookingStatusById = getBookingStatusById(bookingDto.getBookingStatusId());

        Booking booking = new Booking();
        booking.setProduct(productById);
        booking.setUser(userById);
        booking.setDeliveryAddress(bookingDto.getDeliveryAddress());
        booking.setDate(Date.valueOf(LocalDate.now()));
        booking.setTime(Time.valueOf(LocalTime.now()));
        booking.setStatus(bookingStatusById);
        booking.setPrice(productById.getPrice());
        booking.setQuantity(bookingDto.getQuantity());
        Booking savedBooking = bookingRepository.save(booking);
        return bookingEntityToBookingDtoConverter.convert(savedBooking);
    }

    public BookingStatus getBookingStatusById(Integer id) {
        return bookingStatusRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    public String getBookingStatus(Integer id) {
        return bookingStatusRepository.findById(id).get().getName();
    }

    public List<BookingDto> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(bookingEntityToBookingDtoConverter::convert).toList();
    }

    public BookingDto getBookingById(Integer id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        return bookingEntityToBookingDtoConverter.convert(booking);
    }

    public BookingDto updateBookingStatus(Integer id, BookingStatus status) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setStatus(status);
            bookingRepository.save(booking);
            return bookingEntityToBookingDtoConverter.convert(booking);
        }
        throw new RuntimeException("Booking with id '" + id + "' wasn't found");
    }

    public BookingDto updateBooking(Integer id, BookingDto dto) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setDate(new java.sql.Date(dto.getDate().getTime()));
            booking.setTime(timeConverter(dto.getTime()));
            booking.setDeliveryAddress(dto.getDeliveryAddress());
            bookingRepository.save(booking);
            return bookingEntityToBookingDtoConverter.convert(booking);
        }
        throw new RuntimeException("Booking with id '" + id + "' wasn't found");
    }

    public String deleteBooking(Integer id) {
        bookingRepository.deleteById(id);
        return "Booking with id '" + id + "' was deleted";
    }

    @SneakyThrows
    public java.sql.Time timeConverter(String time){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        java.util.Date parsedDate = format.parse(time);
        return new Time(parsedDate.getTime());
    }
}
