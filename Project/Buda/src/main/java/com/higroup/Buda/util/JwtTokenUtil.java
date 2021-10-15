package com.higroup.Buda.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    // public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    //@Value("${jwt.secret}")
	private String secret = Config.secretKey;

    // retrieve username from jwt token
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    // retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // for retrieveing any information from token we will need the secretkey
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // check if the token has expired 
    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    
    // public String generateToken(UserDetails userDetails){
    //     Map<String, Object> claims = new HashMap<String, Object>();
    //     return doGenerateToken(claims, userDetails.getUsername());
    // }

    // generate token for user
    // access token
    public String generataAccessToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("roles", userDetails.getAuthorities());
        return doGenerateToken(claims, userDetails.getUsername(), Config.HoursAccessToken);
    }

    public String generataAccessToken(UserDetails userDetails, Long userID){
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("roles", userDetails.getAuthorities());
        claims.put("userID", userID);
        return doGenerateToken(claims, userDetails.getUsername(), Config.HoursAccessToken);
    }

    // refresh token
    public String generataRefreshToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("roles", userDetails.getAuthorities());
        return doGenerateToken(claims, userDetails.getUsername(), Config.HoursRefreshToken);
    }

    public String generataRefreshToken(UserDetails userDetails, Long userID){
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("roles", userDetails.getAuthorities());
        claims.put("userID", userID);

        return doGenerateToken(claims, userDetails.getUsername(), Config.HoursRefreshToken);
    }

    // while creating the token
    // 1.Define claims of the token, like Issuer, Expiration, Subject, and the ID
    // 2. Sign the JWT úing the HS512 algorithm and secret key
    // 3. According to JWS Compact Serialization 
    // compacton of the JWT to a URL-safe string 
    private String doGenerateToken(Map<String, Object> claims, String subject, int expiredTime){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + expiredTime * 60 * 60 * 1000))
        .signWith(SignatureAlgorithm.HS512, secret).compact();
    }


    // validate token
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    
}
