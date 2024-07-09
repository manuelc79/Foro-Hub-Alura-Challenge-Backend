package foro.desafio.alura_challenge.API.Rest.AluForo.controllers;

import foro.desafio.alura_challenge.API.Rest.AluForo.domain.curso.Curso;
import foro.desafio.alura_challenge.API.Rest.AluForo.domain.curso.CursoRepository;
import foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico.*;
import foro.desafio.alura_challenge.API.Rest.AluForo.domain.usuario.Usuario;
import foro.desafio.alura_challenge.API.Rest.AluForo.domain.usuario.UsuarioRepository;
import foro.desafio.alura_challenge.API.Rest.AluForo.infra.excepciones.UsuarioNoEncontradoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@RestController
@ResponseBody
@RequestMapping("/topicos")
@SecurityRequirement(name= "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;


    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo Tópico en la base de datos")
    public ResponseEntity<DatosDetallesTopico> registroTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                              UriComponentsBuilder uriComponentsBuilder) {
        var response = topicoService.creaTopico(datosRegistroTopico);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Lista los tópicos del sistema ordenados por fecha de creación")
    public ResponseEntity mostrarTopicos(@PageableDefault(size = 10) Pageable paginacion) {
        var response = topicoService.listarTopicos(paginacion);
        return ResponseEntity.ok(response.getContent());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista los topicos de un determinado usuario")
    public ResponseEntity mostrarPorUsuario(@PathVariable @Valid Long id, @PageableDefault(size = 10) Pageable paginacion) {
        var usuario = usuarioRepository.existsById(id);
        if (!usuario) {
            throw new UsuarioNoEncontradoException("Ususario inexistente");
        }
        var response = topicoService.listarTopicosPorUsuario(id, paginacion);
        return ResponseEntity.ok(response.getContent());
    }

    @GetMapping("/curso/{curso_id}")
    @Operation(summary = "Lista los topicos de un determinado curso")
    public ResponseEntity mostrarPorCurso(@PathVariable @Valid Long curso_id, @PageableDefault(size = 10) Pageable paginacion) {
        var usuario = cursoRepository.existsById(curso_id);
        if (!usuario) {
            throw new UsuarioNoEncontradoException("Curso inexistente");
        }
        var response = topicoService.listarTopicosPorCurso(curso_id, paginacion);
        return ResponseEntity.ok(response.getContent());
    }

    @GetMapping("/anio/{year}")
    @Operation(summary = "Lista los topicos de un determinado año")
    public ResponseEntity mostrarPorAnio(@PathVariable @Valid Integer year, @PageableDefault(size = 10) Pageable paginacion) {

        var response = topicoService.listarTopicosPorAnio(year, paginacion);
        return ResponseEntity.ok(response.getContent());
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Modifica un tópico")
    public ResponseEntity modificarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        var response =  topicoService.modificaTopico(datosActualizarTopico);
        return ResponseEntity.ok(response);
    }


}
