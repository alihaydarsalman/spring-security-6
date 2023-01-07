package com.hzyazilimci.security.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author hzyazilimci
 */

@Service
public class JwtManager {

    /**
     *  hardcoded 512-bit hex formatted secret key (generated on https://allkeysgenerator.com/random/security-encryption-key-generator.aspx)
     **/
    private static final String SECRET_KEY = "472D4B6150645367566B5970337336763979244226452948404D6251655468576D" +
                                             "5A7134743777217A25432A462D4A614E645266556A586E3272357538782F41";

    private final long expirationTime = 1000 * 60 * 24;

    public String extractUserName(String token) {
        return extractAnyClaim(token, Claims::getSubject);
    }

    public <T> T extractAnyClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails){

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName= extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractAnyClaim(token,Claims::getExpiration);
    }

    private Claims extractAllClaimsFromToken(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token) //json web signature
                .getBody();
    }

    private Key getSignInKey() {
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
