package foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico.validaciones;

import foro.desafio.alura_challenge.API.Rest.AluForo.domain.curso.CursoRepository;
import foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico.DatosRegistroTopico;
import foro.desafio.alura_challenge.API.Rest.AluForo.infra.errores.ValidacionDeIntegridad;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExisteCurso implements ValidaTopico {

    @Autowired
    CursoRepository cursoRepository;

    @Override
    public void validarTopico (DatosRegistroTopico datos){
        if (datos.curso() == null ) {
            throw new ValidacionDeIntegridad("El topico debe tener un curso asignado");
        }
        var existeCurso = cursoRepository.findById(datos.curso());
        if (existeCurso.isEmpty()) {
            throw new ValidationException("Curso Inexistente");
        }
    }
}
