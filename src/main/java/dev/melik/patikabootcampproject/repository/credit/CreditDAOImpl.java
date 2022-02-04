package dev.melik.patikabootcampproject.repository.credit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditDAOImpl implements CreditDAO{

    private final CreditJpaRepository creditJpaRepository;

    @Override
    public CreditEntity saveCreditApplication(CreditEntity creditEntity) {
        return creditJpaRepository.save(creditEntity);
    }
}
