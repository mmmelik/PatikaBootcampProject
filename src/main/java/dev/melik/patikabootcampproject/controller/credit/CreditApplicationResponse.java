package dev.melik.patikabootcampproject.controller.credit;

import dev.melik.patikabootcampproject.controller.customer.CustomerResponse;
import dev.melik.patikabootcampproject.service.credit.Credit;
import dev.melik.patikabootcampproject.service.credit.CreditStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreditApplicationResponse {

    private Long id;

    public static CreditApplicationResponse fromCredit(Credit credit) {
        return CreditApplicationResponse.builder()
                .id(credit.getId())
                .build();
    }
}
