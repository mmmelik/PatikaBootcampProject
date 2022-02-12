package dev.melik.patikabootcampproject.adapter.jpa.credit;

import dev.melik.patikabootcampproject.domain.credit.Credit;
import dev.melik.patikabootcampproject.domain.exception.ExceptionType;
import dev.melik.patikabootcampproject.domain.exception.PatikaDataNotFoundException;
import dev.melik.patikabootcampproject.domain.port.persistence.CreditPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditJpaAdapter implements CreditPersistencePort {

    private final CreditJpaRepository creditJpaRepository;

    @Override
    public Credit saveCreditApplication(Credit credit) {
        return creditJpaRepository.save(CreditEntity.from(credit)).toModel();
    }

    @Override
    public Credit getCreditById(Long id) {
        Optional<CreditEntity> optionalCreditEntity=creditJpaRepository.findById(id);
        if (optionalCreditEntity.isPresent()){
            return optionalCreditEntity.get().toModel();
        }
        throw new PatikaDataNotFoundException(ExceptionType.CREDIT_NOT_FOUND_EXCEPTION,"Credit "+id+" not found.");
    }

    @Override
    public List<Credit> getCreditsOf(Long tckn) {
        return creditJpaRepository.findByCustomerTckn(tckn).stream()
                .map(CreditEntity::toModel)
                .collect(Collectors.toList());
    }
}
