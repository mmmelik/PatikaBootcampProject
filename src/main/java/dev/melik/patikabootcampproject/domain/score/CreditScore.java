package dev.melik.patikabootcampproject.domain.score;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class CreditScore implements Serializable {

    private Long id;
    private Long tckn;
    private Integer score;
}
