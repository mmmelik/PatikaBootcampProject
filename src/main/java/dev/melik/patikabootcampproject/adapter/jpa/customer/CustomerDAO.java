package dev.melik.patikabootcampproject.adapter.jpa.customer;

public interface CustomerDAO {

    CustomerEntity getCustomerByTCKN(Long tckn);

    CustomerEntity saveCustomer(CustomerEntity customer);

    boolean isPresent(Long tckn);

}
