package dev.melik.patikabootcampproject.domain.score;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditScore implements Serializable {

    private Long id;
    private Long tckn;
    private Integer score;
}
