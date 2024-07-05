package foro.desafio.alura_challenge.API.Rest.AluForo.infra.excepciones;

public class UsuarioNoEncontradoException extends RuntimeException {

    public UsuarioNoEncontradoException (String mensaje) {
        super(mensaje);
    }
}
