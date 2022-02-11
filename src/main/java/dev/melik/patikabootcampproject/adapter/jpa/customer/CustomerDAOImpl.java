package dev.melik.patikabootcampproject.adapter.jpa.customer;

import dev.melik.patikabootcampproject.domain.exception.ExceptionType;
import dev.melik.patikabootcampproject.domain.exception.PatikaDataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerDAOImpl implements CustomerDAO{

    private final CustomerJpaRepository customerJpaRepository;

    @Override
    public CustomerEntity getCustomerByTCKN(Long tckn) {
        Optional<CustomerEntity> optionalCustomerEntity=customerJpaRepository.findByTckn(tckn);
        if (optionalCustomerEntity.isPresent()){
            return optionalCustomerEntity.get();
        }else {
            throw new PatikaDataNotFoundException(ExceptionType.USER_NOT_FOUND_EXCEPTION,"User "+ tckn + " not found.");
        }
    }

    @Override
    public CustomerEntity saveCustomer(CustomerEntity customer) {
        return customerJpaRepository.save(customer);
    }

    @Override
    public boolean isPresent(Long tckn) {
        return customerJpaRepository.existsByTckn(tckn);
    }
}
