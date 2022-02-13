package dev.melik.patikabootcampproject.domain.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtHelper {

    public static String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.SECRET.getBytes())
                .compact();
    }
}
