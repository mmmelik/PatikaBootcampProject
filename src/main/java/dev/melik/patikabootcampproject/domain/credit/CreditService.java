package dev.melik.patikabootcampproject.domain.credit;

import dev.melik.patikabootcampproject.domain.customer.Customer;
import dev.melik.patikabootcampproject.domain.port.persistence.CreditPersistencePort;
import dev.melik.patikabootcampproject.domain.port.persistence.CustomerPersistencePort;
import dev.melik.patikabootcampproject.domain.port.persistence.CreditScorePersistencePort;
import dev.melik.patikabootcampproject.domain.exception.ExceptionType;
import dev.melik.patikabootcampproject.domain.exception.PatikaUnauthorizedRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditService{

    private final CreditPersistencePort creditPersistencePort;

    private final CustomerPersistencePort customerPersistencePort;

    private final CreditScorePersistencePort creditScorePersistencePort;

    @Transactional
    public Credit apply(Long tckn, Credit credit) {

        final int creditLimitMultiplier=4;

        //Customer verification
        Customer customer=customerPersistencePort.getCustomerByTCKN(tckn);
        credit.setCustomer(customer);

        //Credit Calculations
        Integer creditScore=getCreditScore(credit.getCustomer().getTckn());
        if (creditScore<500){
            credit.setCreditStatus(CreditStatus.DENIED);
            credit.setAmount(0d);
        }else if (creditScore<1000){
            credit.setCreditStatus(CreditStatus.GRANTED);
            credit.setAmount(credit.getIncome()>5000?20000d:10000d);
        }else {
            credit.setCreditStatus(CreditStatus.GRANTED);
            credit.setAmount(credit.getIncome()*creditLimitMultiplier);
        }
        return creditPersistencePort.saveCreditApplication(credit);
    }

    public Credit getCreditById(Long tckn, Long id) {
        Credit credit=creditPersistencePort.getCreditById(id);
        if (!tckn.equals(credit.getCustomer().getTckn())){
            throw new PatikaUnauthorizedRequestException(
                    ExceptionType.ILLEGAL_REQUEST_EXCEPTION,
                    "Credit "+id+" does not belong to customer "+tckn);
        }

        credit.setCreditScore(creditScorePersistencePort.getCreditScore(credit.getCustomer().getTckn()));
        return credit;
    }

    public List<Credit> getCreditsOf(Long tckn) {
        return creditPersistencePort.getCreditsOf(tckn);
    }

    public Integer getCreditScore(Long tckn) {
        return creditScorePersistencePort.getCreditScore(tckn);
    }


}
