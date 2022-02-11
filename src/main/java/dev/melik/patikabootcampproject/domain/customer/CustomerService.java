package dev.melik.patikabootcampproject.domain.customer;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    Customer getCustomerByTckn(Long tckn);
}
