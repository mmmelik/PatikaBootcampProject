package dev.melik.patikabootcampproject.service.customer;

public interface CustomerService {

    Customer getCustomerByTCKN(Long tckn);

    Customer saveCustomer(Customer customer);
}
