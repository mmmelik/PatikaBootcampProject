package dev.melik.patikabootcampproject.domain.score;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreditScore {

    private Long id;
    private Long tckn;
    private Integer score;
}
