package foro.desafio.alura_challenge.API.Rest.AluForo.infra.excepciones;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException (String message) {
        super(message);
    }
}
