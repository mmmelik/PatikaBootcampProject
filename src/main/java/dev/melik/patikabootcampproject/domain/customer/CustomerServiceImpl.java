package dev.melik.patikabootcampproject.domain.customer;


import dev.melik.patikabootcampproject.adapter.jpa.customer.CustomerDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerDAO customerDAO;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Customer saveCustomer(Customer customer) {
        //ID validation
        if (!TCKNHelper.validate(customer.getTckn())){
            throw new RuntimeException("Invalid TCKN.");
        }

        //Check if exist
        if (customerDAO.isPresent(customer.getTckn())){
            throw new RuntimeException("Customer already registered");
        }

        //Encode Password
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        return Customer.fromEntity(customerDAO.saveCustomer(customer.toEntity()));
    }

    @Override
    public Customer getCustomerByTckn(Long tckn) {
        return Customer.fromEntity(customerDAO.getCustomerByTCKN(tckn));
    }
}
