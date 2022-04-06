package com.example.warehouse.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserDto {
    @NotBlank(message = "firstname cannot be blank")
    private String firstname;
    @NotBlank(message = "lastname cannot be blank")
    private String lastname;
    @NotBlank(message = "phone number cannot be blank")
    private String number;
    @NotBlank(message = "password cannot be blank")
    private String password;
    @NotBlank(message = "pre password cannot be blank")
    private String prePassword;
    @NotNull(message = "house cannot be blank")
    private List<ForListDto> houseId;
}
