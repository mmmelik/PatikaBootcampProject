package dev.melik.patikabootcampproject.service.customer;

import dev.melik.patikabootcampproject.repository.customer.CustomerEntity;
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


    public static Customer fromEntity(CustomerEntity customerEntity) {
        return Customer.builder()
                .id(customerEntity.getId())
                .tckn(customerEntity.getTckn())
                .password(customerEntity.getPassword())
                .name(customerEntity.getName())
                .phone(customerEntity.getPhone())
                .build();
    }

    public CustomerEntity toEntity() {
        CustomerEntity customerEntity=new CustomerEntity();
        customerEntity.setId(id);
        customerEntity.setPhone(phone);
        customerEntity.setName(name);
        customerEntity.setTckn(tckn);
        customerEntity.setPassword(password);
        return customerEntity;
    }
}
