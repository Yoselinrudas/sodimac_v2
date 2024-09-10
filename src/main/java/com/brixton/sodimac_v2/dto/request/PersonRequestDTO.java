package com.brixton.sodimac_v2.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonRequestDTO {

    private String address;
    private String phone;
    @Email
    @NotNull(message = "Email no puede ser vacio")
    private String email;
}
