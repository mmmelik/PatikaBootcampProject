package dev.melik.patikabootcampproject.adapter.jpa.customer;

import dev.melik.patikabootcampproject.adapter.jpa.common.BaseEntity;
import dev.melik.patikabootcampproject.adapter.jpa.credit.CreditEntity;
import dev.melik.patikabootcampproject.domain.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private Long tckn;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<CreditEntity> creditApplications = new java.util.ArrayList<>();

    public static CustomerEntity from(Customer customer) {
        CustomerEntity customerEntity=new CustomerEntity();
        customerEntity.setId(customer.getId());
        customerEntity.setTckn(customer.getTckn());
        customerEntity.setPassword(customer.getPassword());
        customerEntity.setName(customer.getName());
        customerEntity.setPhone(customer.getPhone());
        return customerEntity;
    }

    public Customer toModel() {
        return Customer.builder()
                .id(id)
                .phone(phone)
                .name(name)
                .tckn(tckn)
                .name(name)
                .password(password)
                .build();
    }
}
