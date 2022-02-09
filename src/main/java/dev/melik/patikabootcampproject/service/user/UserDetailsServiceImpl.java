package dev.melik.patikabootcampproject.service.user;

import dev.melik.patikabootcampproject.repository.customer.CustomerDAO;
import dev.melik.patikabootcampproject.repository.customer.CustomerEntity;
import dev.melik.patikabootcampproject.service.customer.Customer;
import dev.melik.patikabootcampproject.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerDAO customerDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Load user " + username);
        Customer customer=Customer.fromEntity(customerDAO.getCustomerByTCKN(Long.parseLong(username)));
        return User.builder()
                .username(customer.getTckn().toString())
                .password(customer.getPassword())
                .authorities(new ArrayList<>())//TODO: Implement authority
                .build();

    }
}
