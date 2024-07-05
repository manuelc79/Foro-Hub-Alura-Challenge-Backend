package foro.desafio.alura_challenge.API.Rest.AluForo.infra.security;

import foro.desafio.alura_challenge.API.Rest.AluForo.controllers.UsuarioController;
import foro.desafio.alura_challenge.API.Rest.AluForo.domain.usuario.DatosModificaPassword;
import foro.desafio.alura_challenge.API.Rest.AluForo.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.rmi.RemoteException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Obtenemos el token del header
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            String token = authHeader.replace("Bearer ", "");

            var userName = tokenService.getSubject(token); // Extrae el nombre de usuario

            if (userName != null) {
                // Token Valido
                var usuario = usuarioRepository.findByEmail(userName);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities()); // forzamos el inicio de sesion
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

}
