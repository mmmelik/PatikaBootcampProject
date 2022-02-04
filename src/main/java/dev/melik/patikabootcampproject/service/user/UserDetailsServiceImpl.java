package dev.melik.patikabootcampproject.service.user;

import dev.melik.patikabootcampproject.repository.customer.CustomerDAO;
import dev.melik.patikabootcampproject.repository.customer.CustomerEntity;
import dev.melik.patikabootcampproject.service.customer.Customer;
import dev.melik.patikabootcampproject.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerDAO customerDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomerEntity> optionalCustomer=customerDAO.getCustomerByTCKN(Long.parseLong(username));
        if (optionalCustomer.isPresent()){

            Customer customer=Customer.fromEntity(optionalCustomer.get());
            return User.builder()
                    .username(customer.getTckn().toString())
                    .password(customer.getPassword())
                    .build();

        }else {
            throw new UsernameNotFoundException("User "+ username + " not found.");
        }

    }
}
