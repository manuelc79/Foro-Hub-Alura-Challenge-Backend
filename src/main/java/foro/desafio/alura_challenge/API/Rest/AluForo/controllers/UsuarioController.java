package foro.desafio.alura_challenge.API.Rest.AluForo.controllers;

import foro.desafio.alura_challenge.API.Rest.AluForo.domain.usuario.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    //@Operation(summary = "Registra un nuevo usuario en la base de datos" )
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {
      //  try {
            userService.registrarUsuario(datosRegistroUsuario);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuario creado Correctamente");
      //  } catch (Exception e) {
      //      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
      //              .body("Ocurrió un error durante el reguistro de usuario");
      //  }
    }
}
