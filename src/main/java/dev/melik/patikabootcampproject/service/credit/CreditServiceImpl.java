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
import java.util.List;
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
    public Credit apply(Long tckn, Credit credit) {

        final int creditLimitMultiplier=4;

        //Customer verification
        //Create customer if not present
        Customer customer=Customer.fromEntity(customerDAO.getCustomerByTCKN(tckn));
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
        return Credit.fromEntity(creditDAO.saveCreditApplication(credit.toEntity()));

    }

    @Override
    public Credit getCreditById(Long tckn, Long id) {
        Credit credit=Credit.fromEntity(creditDAO.getCreditById(id));
        if (!tckn.equals(credit.getCustomer().getTckn())){
            throw new RuntimeException("Unauthorized request");
        }

        credit.setCreditScore(creditScoreDAO.getCreditScore(credit.getCustomer().getTckn()));
        return credit;
    }

    @Override
    public List<Credit> getCreditsOf(Long tckn) {
        return Credit.fromEntity(customerDAO.getCustomerByTCKN(tckn).getCreditApplications());
    }

    public Integer getCreditScore(Long tckn){
        try {
            return creditScoreDAO.getCreditScore(tckn);
        }catch (CreditScoreNotCalculatedException e){
            //Create new Credit Score
            CreditScoreEntity newCreditScore=new CreditScoreEntity();
            newCreditScore.setTckn(tckn);
            newCreditScore.setScore(new Random().nextInt(0,2000));

            creditScoreDAO.saveCreditScore(newCreditScore);

            return newCreditScore.getScore();
        }
    }

}
