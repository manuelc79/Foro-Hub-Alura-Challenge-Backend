package foro.desafio.alura_challenge.API.Rest.AluForo.infra.excepciones;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String mensaje) {
        super(mensaje);
    }
}
