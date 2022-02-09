package dev.melik.patikabootcampproject.controller.customer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
public class LoginRequest {

    @Min(10000000000L)
    @Max(99999999999L)
    @NotNull
    private Long tckn;

    @NotBlank
    @Size(min = 6)
    private String password;
}
