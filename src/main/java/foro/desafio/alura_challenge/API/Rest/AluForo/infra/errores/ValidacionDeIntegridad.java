package foro.desafio.alura_challenge.API.Rest.AluForo.infra.errores;

public class ValidacionDeIntegridad extends RuntimeException {

    public ValidacionDeIntegridad(String s) {
        super(s);
    }
}
