package dev.melik.patikabootcampproject.adapter.jpa.customer;

import dev.melik.patikabootcampproject.domain.customer.Customer;
import dev.melik.patikabootcampproject.domain.exception.ExceptionType;
import dev.melik.patikabootcampproject.domain.exception.PatikaDataNotFoundException;
import dev.melik.patikabootcampproject.domain.port.persistence.CustomerPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerJpaAdapter implements CustomerPersistencePort {

    private final CustomerJpaRepository customerJpaRepository;

    @Override
    public Customer getCustomerByTCKN(Long tckn) {
        Optional<CustomerEntity> optionalCustomerEntity=customerJpaRepository.findByTckn(tckn);
        if (optionalCustomerEntity.isPresent()){
            return optionalCustomerEntity.get().toModel();
        }else {
            throw new PatikaDataNotFoundException(ExceptionType.USER_NOT_FOUND_EXCEPTION,"User "+ tckn + " not found.");
        }
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerJpaRepository.save(CustomerEntity.from(customer)).toModel();
    }


    @Override
    public boolean isPresent(Long tckn) {
        return customerJpaRepository.existsByTckn(tckn);
    }
}
