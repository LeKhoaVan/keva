package it.ktpm.keva.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {
    private static final String secretKey = "NGuoidentunhungcongiovadisetrolaivoicongiocuaanhtaNGuoidentunhungcongiovadisetrolaivoicongiocuaanhtaNGuoidentunhungcongiovadisetrolaivoicongiocuaanhtaNGuoidentunhungcongiovadisetrolaivoicongiocuaanhta";

    public String generateJwt(String userName){
        Date dateCur = new Date();
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(dateCur)
                .setExpiration(new Date(dateCur.getTime() + 86400000))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)),
                        SignatureAlgorithm.HS256)
                .compact();

    }
}
