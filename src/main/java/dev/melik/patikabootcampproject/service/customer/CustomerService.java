package dev.melik.patikabootcampproject.service.customer;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    Customer getCustomerByTckn(Long tckn);
}
