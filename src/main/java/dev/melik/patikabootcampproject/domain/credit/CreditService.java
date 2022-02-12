package dev.melik.patikabootcampproject.domain.credit;

import dev.melik.patikabootcampproject.domain.customer.Customer;
import dev.melik.patikabootcampproject.domain.port.api.SmsApiPort;
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

    private final SmsApiPort smsApiPort;

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
        credit = creditPersistencePort.saveCreditApplication(credit);

        //Send sms
        smsApiPort.sendCreditApplicationSms(credit.getCustomer().getPhone(),generateCreditApplicationSms(credit));

        return credit;
    }

    private String generateCreditApplicationSms(Credit credit) {
        StringBuilder builder=new StringBuilder();
        builder.append("Sayın ")
                .append(credit.getCustomer().getName())
                .append(", \n ")
                .append(credit.getCreditStatus()==CreditStatus.GRANTED?"Kredi başvurunuz onaylanmıştır.":"Kredi başvurunuz reddedilmiştir.")
                .append(credit.getCreditStatus()==CreditStatus.GRANTED?"Onaylanan miktar "+credit.getAmount():"");

        return builder.toString();
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
