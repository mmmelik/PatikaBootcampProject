package dev.melik.patikabootcampproject.repository.credit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditDAOImpl implements CreditDAO{

    private final CreditJpaRepository creditJpaRepository;

    @Override
    public CreditEntity saveCreditApplication(CreditEntity creditEntity) {
        return creditJpaRepository.save(creditEntity);
    }

    @Override
    public CreditEntity getCreditById(Long id) {
        Optional<CreditEntity> optionalCreditEntity=creditJpaRepository.findById(id);
        if (optionalCreditEntity.isPresent()){
            return optionalCreditEntity.get();
        }
        throw new RuntimeException("Entity not found");
    }
}
