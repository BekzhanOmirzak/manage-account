package com.contacts.manage.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtill implements Serializable {

    public static final String PHONE_NUMBER = "phone_number";
    public static final String USER_ID = "user_id";

    @Value("${jwt.secret}")
    private String secret;

    //retrieve username from jwt token
    public String getUserId(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getPhoneNumber(String token) {
        return getAllClaimsFromToken(token).get(PHONE_NUMBER, String.class);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


}