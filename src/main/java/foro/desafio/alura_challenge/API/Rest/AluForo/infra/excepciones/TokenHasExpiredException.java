package foro.desafio.alura_challenge.API.Rest.AluForo.infra.excepciones;

public class TokenHasExpiredException extends RuntimeException{
    public TokenHasExpiredException(String mensaje) {
        super(mensaje);
    }
}
