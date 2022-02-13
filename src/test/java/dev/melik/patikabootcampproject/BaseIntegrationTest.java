package dev.melik.patikabootcampproject;

import dev.melik.patikabootcampproject.domain.security.JwtHelper;
import dev.melik.patikabootcampproject.domain.security.SecurityConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseIntegrationTest {

    @LocalServerPort
    protected int serverPort;

    @Autowired
    protected TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() {
    }

    protected String getBearerToken(Long tckn){
        return SecurityConstant.TOKEN_PREFIX+" "+JwtHelper.generateToken(String.valueOf(tckn));
    }

    protected boolean validateBearerToken(Long tckn, String token){
        token=token.replace(SecurityConstant.TOKEN_PREFIX, "");
        return String.valueOf(tckn).equals(JwtHelper.getUserFromToken(token));
    }

}
