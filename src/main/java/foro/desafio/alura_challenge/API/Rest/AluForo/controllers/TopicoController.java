package foro.desafio.alura_challenge.API.Rest.AluForo.controllers;

import foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name= "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo Tópico en la base de datos")
    public ResponseEntity<DatosDetallesTopico> registroTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                              UriComponentsBuilder uriComponentsBuilder) {
        var response = topicoService.creaTopico(datosRegistroTopico);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.ok(response);
    }
}
