package dev.melik.patikabootcampproject.service.customer;


import dev.melik.patikabootcampproject.repository.customer.CustomerDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerDAO customerDAO;

    @Override
    public Customer getCustomerByTCKN(Long tckn) {
        return null;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return null;
    }
}
