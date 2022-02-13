package dev.melik.patikabootcampproject.adapter.rest.credit;

import dev.melik.patikabootcampproject.BaseIntegrationTest;
import dev.melik.patikabootcampproject.adapter.jpa.credit.CreditEntity;
import dev.melik.patikabootcampproject.adapter.jpa.credit.CreditJpaRepository;
import dev.melik.patikabootcampproject.adapter.jpa.customer.CustomerEntity;
import dev.melik.patikabootcampproject.adapter.rest.customer.RegisterCustomerResponse;
import dev.melik.patikabootcampproject.domain.credit.CreditStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

class CreditControllerIntegrationTest extends BaseIntegrationTest {


    @Autowired
    CreditJpaRepository creditJpaRepository;

    @Test
    @Sql(scripts = "/insert-customers.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/clear-db.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_apply_for_credit(){
        //given
        CreditApplicationRequest request=new CreditApplicationRequest();
        request.setIncome(123456d);

        HttpHeaders headers=new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION,getBearerToken(12345678914L));
        HttpEntity<CreditApplicationRequest> httpEntity=new HttpEntity<>(request,headers);

        //when
        ResponseEntity<CreditApplicationResponse> response=testRestTemplate
                .exchange("/api/credits/applications",HttpMethod.POST,httpEntity,CreditApplicationResponse.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();

        //validate credit application
        Optional<CreditEntity> credit=creditJpaRepository.findById(response.getBody().getId());
        assertThat(credit).isPresent();
        assertThat(credit.get().getCustomer().getName()).isEqualTo("Customer5");
        assertThat(credit.get().getCreationDate()).isNotNull();
    }

    @Test
    @Sql(scripts = "/insert-customers.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/insert-applications.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/clear-db.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_retrieve_credit_application(){
        //given
        HttpHeaders headers=new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION,getBearerToken(12345678911L));
        HttpEntity<CreditApplicationRequest> httpEntity=new HttpEntity<>(headers);


        //when
        ResponseEntity<CreditResponse> response=testRestTemplate
                .exchange("/api/credits/1003", HttpMethod.GET,httpEntity,CreditResponse.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1003L);
        assertThat(response.getBody())
                .extracting("id","income","customerName","customerTckn","customerPhone","status","amount","creationDate")
                .containsExactly(1003L,12345.0,"Customer2",12345678911L,"2222222222",CreditStatus.GRANTED,22222.0, LocalDateTime.of(2022,2,13,0,0));

    }

    @Test
    @Sql(scripts = "/insert-customers.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/insert-applications.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/clear-db.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_retrieve_credit_applications(){
        //given
        HttpHeaders headers=new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION,getBearerToken(12345678911L));
        HttpEntity<Void> httpEntity=new HttpEntity<>(headers);


        //when
        ResponseEntity<CreditResponse[]> response=testRestTemplate
                .exchange("/api/credits/applications", HttpMethod.GET,httpEntity,CreditResponse[].class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody())
                .extracting("id","income","customerName","customerTckn","customerPhone","status","amount","creationDate")
                .containsExactly(
                        tuple(1003L,12345.0,"Customer2",12345678911L,"2222222222", CreditStatus.GRANTED,22222.0, LocalDateTime.of(2022,2,13,0,0)),
                        tuple(1004L,1235.0,"Customer2",12345678911L,"2222222222",CreditStatus.GRANTED,2222.0, LocalDateTime.of(2022,2,13,0,0))
                );

    }
}