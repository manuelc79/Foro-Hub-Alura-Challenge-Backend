package foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        Boolean estado,
        String autor,
        String curso) {

    public DatosRespuestaTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha_creacion(),
                topico.getEstado(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()

        );
    }
}
