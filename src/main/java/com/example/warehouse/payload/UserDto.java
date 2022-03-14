package com.example.warehouse.payload;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String firstname;
    private String lastname;
    private String number;
    private String password;
    private String prePassword;
    private List<ForListDto> houseId;
}
