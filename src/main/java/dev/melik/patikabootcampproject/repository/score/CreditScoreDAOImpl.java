package dev.melik.patikabootcampproject.repository.score;

import dev.melik.patikabootcampproject.exception.CreditScoreNotCalculatedException;
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
    public Integer getCreditScore(Long tckn) throws CreditScoreNotCalculatedException {

        Optional<CreditScoreEntity> optionalCreditScore=creditScoreJpaRepository.findByTckn(tckn);

        if (optionalCreditScore.isPresent()){
            return optionalCreditScore.get().getScore();
        }else {
            throw new CreditScoreNotCalculatedException();
        }

    }
}
