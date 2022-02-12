package dev.melik.patikabootcampproject.adapter.jpa.score;

import dev.melik.patikabootcampproject.domain.exception.PatikaCreditScoreNotCalculatedException;
import dev.melik.patikabootcampproject.domain.port.persistence.CreditScorePersistencePort;
import dev.melik.patikabootcampproject.domain.score.CreditScore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CreditScoreJpaAdapter implements CreditScorePersistencePort {

    private final CreditScoreJpaRepository creditScoreJpaRepository;

    @Override
    public void saveCreditScore(CreditScore creditScore) {
        creditScoreJpaRepository.save(CreditScoreEntity.from(creditScore));
    }

    @Override
    public Integer getCreditScore(Long tckn) throws PatikaCreditScoreNotCalculatedException {

        Optional<CreditScoreEntity> optionalCreditScore=creditScoreJpaRepository.findByTckn(tckn);

        if (optionalCreditScore.isPresent()){
            return optionalCreditScore.get().getScore();
        }else {
            CreditScoreEntity newCreditScore=new CreditScoreEntity();
            newCreditScore.setTckn(tckn);
            newCreditScore.setScore(new Random().nextInt(0,2000));

            saveCreditScore(newCreditScore.toModel());
            return newCreditScore.getScore();
        }

    }
}
