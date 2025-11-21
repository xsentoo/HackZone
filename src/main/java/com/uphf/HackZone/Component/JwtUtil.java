package com.uphf.HackZone.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expiration ;

    public String generateToken(String userMail){
            return Jwts.builder()
                    .setSubject(userMail)//info qu'il stock le token le batard
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis()+expiration))
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();


    }
    //cette class on utilise pour extraire mail d'un token
    public String extractUserMail(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    // ici on verifie si le token est valide ou pas en comparent sont mail et le token
    public boolean validateToken(String token, String userMail){
            String mail = extractUserMail(token);
            return mail.equals(userMail) && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token){
        Date expirationDate = Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expirationDate.before(new Date());
    }

}
