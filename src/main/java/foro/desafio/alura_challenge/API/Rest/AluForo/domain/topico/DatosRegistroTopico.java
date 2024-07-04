package foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(

        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Long autor,
        @NotBlank
        String curso) {
}
