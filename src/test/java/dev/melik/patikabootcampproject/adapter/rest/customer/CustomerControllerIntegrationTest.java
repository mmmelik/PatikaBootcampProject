package dev.melik.patikabootcampproject.adapter.rest.customer;

import dev.melik.patikabootcampproject.BaseIntegrationTest;
import dev.melik.patikabootcampproject.adapter.jpa.customer.CustomerEntity;
import dev.melik.patikabootcampproject.adapter.jpa.customer.CustomerJpaRepository;
import dev.melik.patikabootcampproject.adapter.rest.common.ExceptionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class CustomerControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    CustomerJpaRepository customerJpaRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @Sql(scripts = "/clear-db.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_register() {
        //given
        RegisterCustomerRequest customerRequest=new RegisterCustomerRequest();
        customerRequest.setName("Dummy1");
        customerRequest.setPhone("1111111111");
        customerRequest.setTckn(17211394446L);//Should be valid
        customerRequest.setPassword("123456");

        //when
        ResponseEntity<RegisterCustomerResponse> response=testRestTemplate
                .postForEntity("/api/register",customerRequest,RegisterCustomerResponse.class);


        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getTckn()).isEqualTo(customerRequest.getTckn());

        Optional<CustomerEntity> customer=customerJpaRepository.findById(response.getBody().getId());
        assertThat(customer).isPresent();
        assertThat(customer.get().getTckn()).isEqualTo(customerRequest.getTckn());
        assertThat(customer.get().getCreationDate()).isNotNull();

    }

    @Test
    @Sql(scripts = "/insert-customers.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/clear-db.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_block_customer_duplication(){
        //when
        RegisterCustomerRequest customerRequest=new RegisterCustomerRequest();
        customerRequest.setName("Customer1");
        customerRequest.setTckn(48408924504L);
        customerRequest.setPassword("123456");
        customerRequest.setPhone("1111111111");

        //when
        ResponseEntity<ExceptionResponse> response=testRestTemplate
                .postForEntity("/api/register",customerRequest, ExceptionResponse.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCode()).isEqualTo(9003);

    }

    @Test
    @Sql(scripts = "/clear-db.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_login(){
        //given
        CustomerEntity customerEntity=new CustomerEntity();
        customerEntity.setTckn(12345678910L);
        customerEntity.setName("Dummy");
        customerEntity.setPhone("1111111111");
        customerEntity.setPassword(passwordEncoder.encode("SecurePassword"));
        customerJpaRepository.save(customerEntity);

        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setTckn(12345678910L);
        loginRequest.setPassword("SecurePassword");

        //when
        ResponseEntity<Object> response=testRestTemplate.postForEntity("/api/login",loginRequest,Object.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNull();
        assertTrue(response.getHeaders().containsKey("Authorization"));
        assertTrue(validateBearerToken(loginRequest.getTckn(),response.getHeaders().getFirst("Authorization")));

    }

    @Test
    @Sql(scripts = "/clear-db.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_not_login_with_wrong_password(){
        //given
        CustomerEntity customerEntity=new CustomerEntity();
        customerEntity.setTckn(12345678910L);
        customerEntity.setName("Dummy");
        customerEntity.setPhone("1111111111");
        customerEntity.setPassword(passwordEncoder.encode("SecurePassword"));
        customerJpaRepository.save(customerEntity);

        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setTckn(12345678910L);
        loginRequest.setPassword("NotSecurePassword");

        //when
        ResponseEntity<Object> response=testRestTemplate.postForEntity("/api/login",loginRequest,Object.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(response.getBody()).isNull();
        assertFalse(response.getHeaders().containsKey("Authorization"));

    }
}