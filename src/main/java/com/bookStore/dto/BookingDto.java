package com.bookStore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BookingDto {

    private int id;

    @JsonProperty("product_id")
    private Integer productId;

    @JsonProperty("user_id")
    private Integer userId;

    private String deliveryAddress;

    @JsonProperty("booking_status")
    private Integer bookingStatusId;

    private Integer quantity;
    private String bookingStatusName;
    private String productTitle;
    private Date date;
    private String time;
    private float price;

}
