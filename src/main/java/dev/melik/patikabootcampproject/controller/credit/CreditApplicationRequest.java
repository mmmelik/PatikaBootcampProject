package dev.melik.patikabootcampproject.controller.credit;

import dev.melik.patikabootcampproject.controller.customer.CustomerRequest;
import dev.melik.patikabootcampproject.service.credit.Credit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CreditApplicationRequest {

    @Valid
    @NotNull
    private CustomerRequest customer;

    @NotNull
    private Double income;

    public Credit toCredit() {
        return Credit.builder()
                .customer(customer.toCustomer())
                .income(income)
                .build();
    }
}
