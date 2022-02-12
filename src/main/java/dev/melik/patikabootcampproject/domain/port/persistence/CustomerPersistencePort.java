package dev.melik.patikabootcampproject.domain.port.persistence;

import dev.melik.patikabootcampproject.adapter.jpa.customer.CustomerEntity;
import dev.melik.patikabootcampproject.domain.credit.Credit;
import dev.melik.patikabootcampproject.domain.customer.Customer;

import java.util.List;

public interface CustomerPersistencePort {

    Customer getCustomerByTCKN(Long tckn);

    Customer saveCustomer(Customer customer);

    boolean isPresent(Long tckn);

}
