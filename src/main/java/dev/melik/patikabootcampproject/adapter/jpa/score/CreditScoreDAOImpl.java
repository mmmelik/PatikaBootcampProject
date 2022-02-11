package dev.melik.patikabootcampproject.adapter.jpa.score;

import dev.melik.patikabootcampproject.domain.exception.PatikaCreditScoreNotCalculatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditScoreDAOImpl implements CreditScoreDAO{

    private final CreditScoreJpaRepository creditScoreJpaRepository;

    @Override
    public void saveCreditScore(CreditScoreEntity creditScore) {
        creditScoreJpaRepository.save(creditScore);
    }

    @Override
    public Integer getCreditScore(Long tckn) throws PatikaCreditScoreNotCalculatedException {

        Optional<CreditScoreEntity> optionalCreditScore=creditScoreJpaRepository.findByTckn(tckn);

        if (optionalCreditScore.isPresent()){
            return optionalCreditScore.get().getScore();
        }else {
            throw new PatikaCreditScoreNotCalculatedException();
        }

    }
}
