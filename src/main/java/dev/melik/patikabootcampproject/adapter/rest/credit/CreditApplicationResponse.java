package dev.melik.patikabootcampproject.adapter.rest.credit;

import dev.melik.patikabootcampproject.domain.credit.Credit;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditApplicationResponse {

    private Long id;

    public static CreditApplicationResponse fromCredit(Credit credit) {
        return CreditApplicationResponse.builder()
                .id(credit.getId())
                .build();
    }
}
