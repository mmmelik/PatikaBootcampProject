package dev.melik.patikabootcampproject.adapter.rest.credit;

import dev.melik.patikabootcampproject.domain.credit.Credit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CreditApplicationRequest {

    @NotNull
    private Double income;

    public Credit toCredit() {
        return Credit.builder()
                .income(income)
                .build();
    }
}
