package foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico;

import java.time.LocalDateTime;

public record DatosDetallesTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        Boolean estado,
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
