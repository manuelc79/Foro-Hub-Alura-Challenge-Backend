package foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico.validaciones;

import foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico.DatosRegistroTopico;
import foro.desafio.alura_challenge.API.Rest.AluForo.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExisteUsuario implements ValidaTopico {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void validarTopico (DatosRegistroTopico datos) {
        var existeUsuario = usuarioRepository.existsById(datos.autor());

        if (!existeUsuario) {
            throw new ValidationException("Autor inexistente o no registrado en el sistema");
        }
    }
}
