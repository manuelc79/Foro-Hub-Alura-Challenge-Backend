package foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosDetallesTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        Integer estado,
        Long autor,
        Long curso) {

    public DatosDetallesTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha_creacion(),
                topico.getEstado(),
                topico.getAutor().getId(),
                topico.getCurso().getId()
        );
    }
}
