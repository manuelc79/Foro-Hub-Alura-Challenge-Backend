package foro.desafio.alura_challenge.API.Rest.AluForo.controllers;

import foro.desafio.alura_challenge.API.Rest.AluForo.domain.usuario.DatosAutenticacionUsuario;
import foro.desafio.alura_challenge.API.Rest.AluForo.domain.usuario.Usuario;
import foro.desafio.alura_challenge.API.Rest.AluForo.infra.security.DatosJWTToken;
import foro.desafio.alura_challenge.API.Rest.AluForo.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.email(),
                datosAutenticacionUsuario.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
