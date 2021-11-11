package com.higroup.Buda.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.higroup.Buda.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
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

    // get userID from jwttoken
    public Long getUserIDFromToken(String token){
        Claims claims = getAllClaimsFromToken(token);
        Long userID = claims.get("userID", Long.class);
        return userID;
    }

    // get staffID from jwttoken
    public Long getStaffIDFromToken(String token){
        Claims claims = getAllClaimsFromToken(token);
        Long staffID = claims.get("staffID", Long.class);
        return staffID;
    }
    // get staff uuid from jwttoken
    public String getStaffUUIDFromToken(String token){
        Claims claims = getAllClaimsFromToken(token);
        String staffUUID = claims.get("staffID", String.class);
        return staffUUID;
    }

    // get roles from jwttoken
    public Collection<?> getRolesFromToken(String token){
        Claims claims = getAllClaimsFromToken(token);
        Collection<?> authorities = claims.get("roles", Collection.class);
        return authorities; 
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
    public String generateAccessToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("roles", userDetails.getAuthorities());
        return doGenerateToken(claims, userDetails.getUsername(), Config.HoursAccessToken);
    }

    public String generateAccessToken(UserDetails userDetails, Long userID){
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("roles", userDetails.getAuthorities());
        claims.put("userID", userID);
        return doGenerateToken(claims, userDetails.getUsername(), Config.HoursAccessToken);
    }

    // refresh token
    public String generateRefreshToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("roles", userDetails.getAuthorities());
        return doGenerateToken(claims, userDetails.getUsername(), Config.HoursRefreshToken);
    }

    public String generateRefreshToken(UserDetails userDetails, Long userID){
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("roles", userDetails.getAuthorities());
        claims.put("userID", userID);

        return doGenerateToken(claims, userDetails.getUsername(), Config.HoursRefreshToken);
    }

    public String generateToken(UserDetails userDetails, Map<String, Object> claims, int HourExpiredToken){
        claims.put("roles", userDetails.getAuthorities());
        return doGenerateToken(claims, userDetails.getUsername(), HourExpiredToken);
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

    private String doGenerateToken(Map<String, Object> claims)
    {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    // validate token
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        try {
            Claims claims = getAllClaimsFromToken(token);
            String systemToken = doGenerateToken(claims);
            return (systemToken.equals(token) && username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (JwtException | IllegalArgumentException e) {
        }
        return false;
        
    }
    public boolean isValid(String token)
    {
        try {
            Claims claims = getAllClaimsFromToken(token);
            String systemToken = doGenerateToken(claims);
            return ((systemToken.equals(token)) && (!isTokenExpired(token)));
        } catch (JwtException | IllegalArgumentException e) {
        }
        return false;
    }

    
}
