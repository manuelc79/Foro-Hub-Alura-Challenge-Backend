package foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico.validaciones;

import foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico.DatosRegistroTopico;
import foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExisteTopico implements ValidaTopico{

    @Autowired
    TopicoRepository topicoRepository;

    @Override
    public void validarTopico(DatosRegistroTopico datos) {
        var existeTitulo = topicoRepository.buscarDuplicado(datos.titulo(), datos.mensaje());
        System.out.println("Existe Titulo?: " + existeTitulo);
        if (existeTitulo != null){
            throw new ValidationException("Ya existe un topico con ese Titulo y/o Mensaje");
        }
    }
}
