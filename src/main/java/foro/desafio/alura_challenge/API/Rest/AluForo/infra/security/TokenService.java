package foro.desafio.alura_challenge.API.Rest.AluForo.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import foro.desafio.alura_challenge.API.Rest.AluForo.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            var token = JWT.create()
                    .withIssuer("Foro Alura")
                    .withSubject(usuario.getUsername())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaDeExpiracion())
                    .sign(algorithm);
            System.out.println(token); // eliminar despues de Debuguear
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {

        if (token == null){
            throw new RuntimeException();
        }
        System.out.println(token);
        DecodedJWT verifier = null;
        try {
            System.out.println("ApiSecret: " + apiSecret);
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // Validando firma
            System.out.println("Algoritmo: "+ algorithm);
            verifier = JWT.require(algorithm)
                    .withIssuer("Foro Alura")
                    .build()
                    .verify(token);
            System.out.println("Verificador: " + verifier.getSubject());
            verifier.getSubject();

        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier == null) { //.getSubject()
            throw new RuntimeException("Verificación Invalida");
        }
        return  verifier.getSubject();
    }

    private Instant generarFechaDeExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
