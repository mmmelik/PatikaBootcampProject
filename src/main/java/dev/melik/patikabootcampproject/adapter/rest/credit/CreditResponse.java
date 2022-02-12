package dev.melik.patikabootcampproject.adapter.rest.credit;

import dev.melik.patikabootcampproject.domain.credit.Credit;
import dev.melik.patikabootcampproject.domain.credit.CreditStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class CreditResponse {

    private Long id;

    private Double income;

    private String customerName;

    private Long customerTckn;

    private String customerPhone;

    private CreditStatus status;

    private Double amount;

    private LocalDateTime creationDate;

    public static CreditResponse fromCredit(Credit credit) {
        return CreditResponse.builder()
                .id(credit.getId())
                .income(credit.getIncome())
                .creationDate(credit.getCreationDate())
                .status(credit.getCreditStatus())
                .customerPhone(credit.getCustomer().getPhone())
                .customerTckn(credit.getCustomer().getTckn())
                .customerName(credit.getCustomer().getName())
                .amount(credit.getAmount())
                .build();
    }

    public static List<CreditResponse> fromCredit(List<Credit> credits){
        return credits.stream()
                .map(CreditResponse::fromCredit)
                .collect(Collectors.toList());
    }
}
