package dev.melik.patikabootcampproject.service.credit;

import dev.melik.patikabootcampproject.exception.CreditScoreNotCalculatedException;
import dev.melik.patikabootcampproject.repository.credit.CreditDAO;
import dev.melik.patikabootcampproject.repository.customer.CustomerDAO;
import dev.melik.patikabootcampproject.repository.customer.CustomerEntity;
import dev.melik.patikabootcampproject.repository.score.CreditScoreDAO;
import dev.melik.patikabootcampproject.repository.score.CreditScoreEntity;
import dev.melik.patikabootcampproject.service.customer.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService{

    private final CreditDAO creditDAO;

    private final CustomerDAO customerDAO;

    private final CreditScoreDAO creditScoreDAO;

    @Override
    @Transactional
    public Credit apply(Credit credit) {

        final int creditLimitMultiplier=4;

        //Customer verification
        //Create customer if not present
        Customer customer=credit.getCustomer();
        Optional<CustomerEntity> optionalCustomer=customerDAO.getCustomerByTCKN(customer.getTckn());
        optionalCustomer.ifPresentOrElse(c -> credit.setCustomer(Customer.fromEntity(c)),()->{
            Customer createdCustomer = Customer.fromEntity(customerDAO.saveCustomer(customer.toEntity()));
            credit.setCustomer(createdCustomer);
        });

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
        return Credit.fromEntity(creditDAO.saveCreditApplication(credit.toEntity()));

    }

    private Integer getCreditScore(Long tckn){
        try {
            return creditScoreDAO.getCreditScore(tckn);
        }catch (CreditScoreNotCalculatedException e){
            //Create new Credit Score
            CreditScoreEntity newCreditScore=new CreditScoreEntity();
            newCreditScore.setTckn(tckn);
            newCreditScore.setScore(new Random().nextInt(0,1001));

            creditScoreDAO.saveCreditScore(newCreditScore);

            return newCreditScore.getScore();
        }
    }

}
