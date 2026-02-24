package tw.lab.Spring07.util;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtToken {
    private static final long EXP_TIME = 10 * 60 * 1000;
    private static final String SECURT = "KokohaMo123456789zxcvbnmasdfghjklqwertyuiop";
    private static final Key KEY = Keys.hmacShaKeyFor(SECURT.getBytes());

    public static String createToken(String subject) {
        String token = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXP_TIME))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    public static String parseToken(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(KEY).build();
        String subject = parser.parseClaimsJws(token).getBody().getSubject();

        return subject;
    }
}
