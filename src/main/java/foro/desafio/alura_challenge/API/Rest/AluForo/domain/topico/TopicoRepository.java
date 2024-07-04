package foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico;

import foro.desafio.alura_challenge.API.Rest.AluForo.domain.curso.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    @Query("""
            SELECT t FROM Topico t
            WHERE t.titulo = :titulo
            AND
            t.mensaje = :mensaje
            """)
    Topico buscarDuplicado(String titulo, String mensaje);

    Page <Topico> findAllByCurso(Curso curso, Pageable paginacion);

    Page <Topico> findAllByEstadoTrue(Pageable paginacion);

    Page<Topico> findAllByEstadoFalse(Pageable paginacion);
}
