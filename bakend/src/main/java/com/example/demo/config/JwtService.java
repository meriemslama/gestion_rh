package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY="576E5A7234743777217A25432A462D4A614E645267556B58703273357638782F"; //site secretkeysgenertor

    public String extractUsername(String token)
    {
        return extractClaim(token , Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims ,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>() , userDetails);
    }
    //methode pour generer le token
    public String generateToken(
        Map<String ,Object> extrClaims,
                UserDetails userDetails){
        return Jwts.builder().setClaims(extrClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +1000*60*24))
                .signWith(getSigningKey() , SignatureAlgorithm.HS256)
                .compact();//generate and return the token

    }
    //methode pour valider token
    public Boolean isTokenValid(String token , UserDetails userDetails) //pour verifier si token (input) est eq a userdetails
    {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpred(token);
    }

    private boolean isTokenExpred(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims (String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[]keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
