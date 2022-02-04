package dev.melik.patikabootcampproject.controller.customer;

import dev.melik.patikabootcampproject.service.customer.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
public class CustomerRequest {


    private Long id;

    @Min(10000000000L)
    @Max(99999999999L)
    @NotNull
    private Long tckn;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    public Customer toCustomer() {
        return Customer.builder()
                .id(id)
                .name(name)
                .phone(phone)
                .tckn(tckn)
                .build();
    }
}
