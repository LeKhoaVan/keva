package it.ktpm.keva.security.jwt;

import com.auth0.jwt.algorithms.Algorithm;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import it.ktpm.keva.common.exception.KevaBusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import java.util.Date;


@Slf4j
@Component
public class JwtUtils {
    private final String secretKey = "NGuoidentunhungcongiovadisetrolaivoicongiocuaanhtaNGuoidentunhungcongiovadisetrolaivoicongiocuaanhtaNGuoidentunhungcongiovadisetrolaivoicongiocuaanhtaNGuoidentunhungcongiovadisetrolaivoicongiocuaanhta";
    private byte[] Key = secretKey.getBytes(StandardCharsets.UTF_8);
    //dung thu vien cu (old library)
//    public String generateJwt(String userName){
//        Date dateCur = new Date();
//        return Jwts.builder()
//                .setSubject(userName)
//                .setIssuedAt(dateCur)
//                .setExpiration(new Date(dateCur.getTime() + 86400000))
//                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)),
//                        SignatureAlgorithm.HS256)
//                .compact();
//
//    }

    //tạo privateKey và publicKey nếu cần
//    public KeyPair generateKy(String secretKey){
//
//        KeyPair pair = null;
//        try {
//            KeyPairGenerator generator = null;
//            generator = KeyPairGenerator.getInstance("RSA");
//            generator.initialize(2048);
//            pair = generator.generateKeyPair();
//
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//        return pair;
//    }


    public String generateJwt(String userName){
        Date dateCur = new Date();
        try {
            Algorithm algorithm = Algorithm.HMAC512(Key);
            String jwt = JWT.create()
                    .withIssuedAt(dateCur)
                    .withIssuer("LeVan")
                    .withSubject(userName)
                    .withExpiresAt(new Date(dateCur.getTime() + 86400000))
                    .sign(algorithm);
            return jwt;
        } catch (JWTCreationException exception){
            throw new KevaBusinessException("Create JWT error "+exception.getMessage());
        }
    }

    public boolean validateJwt(String token){
//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC512(Key);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("LeVan")
                    .build();

            decodedJWT = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception){
            log.error("Verification JWT error: "+ exception.getMessage());
            return false;
        }
    }

    public String getToken(HttpServletRequest request){
        String jwt = request.getHeader("Authorization");

        if (jwt == null)
            return null;

        return jwt.substring("Bearer ".length(), jwt.length());
    }


    public String getUserName(String token) {
        DecodedJWT decodedJWT = null;
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey.getBytes(StandardCharsets.UTF_8));
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("LeVan")
                    .build();

            decodedJWT = verifier.verify(token);

        } catch (JWTVerificationException exception) {
            log.error("Get username in verification JWT error: "+ exception.getMessage());
        }
        if (decodedJWT != null){
            return decodedJWT.getSubject();
        }
        return null;

    }

}
