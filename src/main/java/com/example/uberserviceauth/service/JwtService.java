package com.example.uberserviceauth.service;

import com.example.uberserviceauth.models.BaseModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService implements CommandLineRunner {
    @Value("${jwt.expiry}")
    private int expiry;
    @Value("${jwt.secret}")
    private String  SECRET;

    //This is a method creates a new JWT token for us based on payload

    private String createToken(Map<String, Object> payload,String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry*1000L);
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
        return Jwts.builder()
                .setClaims(payload)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiryDate)
                .setSubject(email)
                .signWith(getSecretKey())
                .compact();
    }
    private Claims extractAllpayLoads(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSecretKey())
                .parseClaimsJws(token)
                .getBody();

    }

    private <T> T extractClaim(String token , Function<Claims, T> claimsResolver) {
          final Claims claims = extractAllpayLoads(token);
          return claimsResolver.apply(claims);
    }

    private  Date extractExpiration(String token) {
        return  extractClaim(token , Claims ::getExpiration);
    }
    private String extractEmai(String token){
        return extractClaim(token , Claims ::getSubject);
    }
    public boolean validateToken(String token , String email) {
        final String userEmailFetchedFromToken = extractEmai(token);
        return (userEmailFetchedFromToken.equals(email) && !isTokenExpired(token));

    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
    @Override
    public void run(String... args) throws Exception {
        Map<String,Object> mp = new HashMap<>();
        mp.put("email" ,"a@b.com");
        mp.put("phoneNumber" ,"123456");
        String result = createToken(mp,"AKG");
        System.out.println("Genearated Token is" +result);
    }



}
