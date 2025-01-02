package com.bookStore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String login;
    private String password;

    @JsonProperty("role_id")
    private Integer roleId;
}
