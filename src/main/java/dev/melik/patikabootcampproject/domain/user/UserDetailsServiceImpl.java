package dev.melik.patikabootcampproject.domain.user;

import dev.melik.patikabootcampproject.domain.port.persistence.CustomerPersistencePort;
import dev.melik.patikabootcampproject.domain.customer.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerPersistencePort customerPersistencePort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Load user " + username);
        Customer customer=customerPersistencePort.getCustomerByTCKN(Long.parseLong(username));
        return User.builder()
                .username(customer.getTckn().toString())
                .password(customer.getPassword())
                .authorities(new ArrayList<>())//TODO: Implement authority
                .build();

    }
}
