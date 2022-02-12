package dev.melik.patikabootcampproject.adapter.jpa.score;

import dev.melik.patikabootcampproject.adapter.jpa.common.BaseEntity;
import dev.melik.patikabootcampproject.domain.score.CreditScore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "credit_score")
public class CreditScoreEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private Long tckn;

    @Column(nullable = false)
    private Integer score;

    public static CreditScoreEntity from(CreditScore creditScore) {
        CreditScoreEntity entity=new CreditScoreEntity();
        entity.setId(creditScore.getId());
        entity.setTckn(creditScore.getTckn());
        entity.setScore(creditScore.getScore());
        return entity;
    }

    public CreditScore toModel() {
        return CreditScore.builder()
                .id(id)
                .score(score)
                .tckn(tckn)
                .build();
    }
}
