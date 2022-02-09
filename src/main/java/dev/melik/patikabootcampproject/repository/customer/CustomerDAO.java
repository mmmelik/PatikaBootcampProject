package dev.melik.patikabootcampproject.repository.customer;

import java.util.Optional;

public interface CustomerDAO {

    CustomerEntity getCustomerByTCKN(Long tckn);

    CustomerEntity saveCustomer(CustomerEntity customer);

    boolean isPresent(Long tckn);

}
