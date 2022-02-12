package dev.melik.patikabootcampproject.domain.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Customer {

    private Long id;

    private Long tckn;

    private String password;

    private String name;

    private String phone;

}
