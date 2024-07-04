package foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarTopico (
    @NotBlank
    Long id,
    String titulo,
    String mensaje
    ){

}
