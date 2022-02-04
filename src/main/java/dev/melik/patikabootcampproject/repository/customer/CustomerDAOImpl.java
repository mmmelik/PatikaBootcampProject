package dev.melik.patikabootcampproject.repository.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerDAOImpl implements CustomerDAO{

    private final CustomerJpaRepository customerJpaRepository;

    @Override
    public Optional<CustomerEntity> getCustomerByTCKN(Long tckn) {
        return customerJpaRepository.findByTckn(tckn);
    }

    @Override
    public CustomerEntity saveCustomer(CustomerEntity customer) {
        return customerJpaRepository.save(customer);
    }
}
