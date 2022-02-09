package dev.melik.patikabootcampproject.controller.customer;

import dev.melik.patikabootcampproject.service.customer.Customer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
public class RegisterCustomerRequest {


    @Min(10000000000L)
    @Max(99999999999L)
    @NotNull
    private Long tckn;

    @NotBlank
    @Size(min = 3)
    private String name;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    @Size(min = 10,max = 10)
    private String phone;

    public Customer toCustomer() {
        return Customer.builder()
                .name(name)
                .password(password)
                .phone(phone)
                .tckn(tckn)
                .build();
    }
}
