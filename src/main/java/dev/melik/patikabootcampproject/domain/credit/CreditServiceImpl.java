package dev.melik.patikabootcampproject.domain.credit;

import dev.melik.patikabootcampproject.adapter.repository.CreditStatus;
import dev.melik.patikabootcampproject.domain.customer.Customer;
import dev.melik.patikabootcampproject.domain.exception.PatikaCreditScoreNotCalculatedException;
import dev.melik.patikabootcampproject.adapter.jpa.credit.CreditDAO;
import dev.melik.patikabootcampproject.adapter.jpa.customer.CustomerDAO;
import dev.melik.patikabootcampproject.adapter.jpa.score.CreditScoreDAO;
import dev.melik.patikabootcampproject.adapter.jpa.score.CreditScoreEntity;
import dev.melik.patikabootcampproject.domain.exception.ExceptionType;
import dev.melik.patikabootcampproject.domain.exception.PatikaUnauthorizedRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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
            throw new PatikaUnauthorizedRequestException(
                    ExceptionType.ILLEGAL_REQUEST_EXCEPTION,
                    "Credit "+id+" does not belong to customer "+tckn);
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
        }catch (PatikaCreditScoreNotCalculatedException e){
            //Create new Credit Score
            CreditScoreEntity newCreditScore=new CreditScoreEntity();
            newCreditScore.setTckn(tckn);
            newCreditScore.setScore(new Random().nextInt(0,2000));

            creditScoreDAO.saveCreditScore(newCreditScore);

            return newCreditScore.getScore();
        }
    }

}
