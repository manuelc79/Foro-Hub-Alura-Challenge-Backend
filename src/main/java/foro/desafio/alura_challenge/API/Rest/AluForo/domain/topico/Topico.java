package foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico;

import foro.desafio.alura_challenge.API.Rest.AluForo.domain.curso.Curso;
import foro.desafio.alura_challenge.API.Rest.AluForo.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @Column(unique = true)
    private String mensaje;
    private LocalDateTime fecha_creacion;
    private Integer estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Topico(String titulo, String mensaje, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fecha_creacion = LocalDateTime.now();
        this.estado = 0;
        this.autor = autor;
        this.curso = curso;
    }

    public void actualizarTopico(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null) {
            this.mensaje = datosActualizarTopico.mensaje();
        }
    }

    public void serEstado(){
        this.estado = (estado + 1) % 2;
    }


}
