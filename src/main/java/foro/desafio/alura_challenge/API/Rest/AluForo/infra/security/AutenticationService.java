package foro.desafio.alura_challenge.API.Rest.AluForo.infra.security;

import foro.desafio.alura_challenge.API.Rest.AluForo.domain.usuario.UsuarioRepository;
import foro.desafio.alura_challenge.API.Rest.AluForo.infra.excepciones.UsuarioNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticationService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var userMail = usuarioRepository.findByEmail(username);
       if (userMail != null) {
           return userMail;
       }
       throw new UsuarioNoEncontradoException("Usuario inexistente");
    }
}
