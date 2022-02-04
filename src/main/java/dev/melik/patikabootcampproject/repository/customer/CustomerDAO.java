package dev.melik.patikabootcampproject.repository.customer;

import java.util.Optional;

public interface CustomerDAO {

    Optional<CustomerEntity> getCustomerByTCKN(Long tckn);

    CustomerEntity saveCustomer(CustomerEntity customer);
}
