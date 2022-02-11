package dev.melik.patikabootcampproject.adapter.rest.customer;

import dev.melik.patikabootcampproject.domain.customer.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class RegisterCustomerResponse {

    private Long id;

    private Long tckn;

    public static RegisterCustomerResponse fromCustomer(Customer customer) {
        return RegisterCustomerResponse.builder()
                .id(customer.getId())
                .tckn(customer.getTckn())
                .build();
    }
}
