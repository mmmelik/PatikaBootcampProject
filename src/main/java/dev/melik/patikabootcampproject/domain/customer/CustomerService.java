package dev.melik.patikabootcampproject.domain.customer;


import dev.melik.patikabootcampproject.domain.exception.ExceptionType;
import dev.melik.patikabootcampproject.domain.exception.PatikaRegistrationException;
import dev.melik.patikabootcampproject.domain.port.persistence.CustomerPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerPersistencePort customerPersistencePort;

    private final PasswordEncoder passwordEncoder;

    public Customer saveCustomer(Customer customer) {
        //ID validation
        if (!TCKNHelper.validate(customer.getTckn())){
            throw new PatikaRegistrationException(ExceptionType.BAD_ID_NUMBER_EXCEPTION,"Provided id is invalid");
        }

        //Check if exist
        if (customerPersistencePort.isPresent(customer.getTckn())){
            throw new PatikaRegistrationException(ExceptionType.USER_EXIST_EXCEPTION,"Customer " +customer.getTckn()+ " already registered");
        }

        //Encode Password
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        return customerPersistencePort.saveCustomer(customer);
    }

    public Customer getCustomerByTckn(Long tckn) {
        return customerPersistencePort.getCustomerByTCKN(tckn);
    }
}
