package foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico;

import foro.desafio.alura_challenge.API.Rest.AluForo.domain.curso.CursoRepository;
import foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico.validaciones.ValidaTopico;
import foro.desafio.alura_challenge.API.Rest.AluForo.domain.usuario.UsuarioRepository;
import foro.desafio.alura_challenge.API.Rest.AluForo.infra.errores.ValidacionDeIntegridad;
import foro.desafio.alura_challenge.API.Rest.AluForo.infra.excepciones.UsuarioNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TopicoService {

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    private List<ValidaTopico> validaTopicoList;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public DatosDetallesTopico creaTopico(DatosRegistroTopico datosRegistroTopico) {

        validaTopicoList.forEach(v -> v.validarTopico(datosRegistroTopico));

        var titulo = datosRegistroTopico.titulo();
        var mensaje = datosRegistroTopico.mensaje();
        var autor = usuarioRepository.getReferenceById(datosRegistroTopico.autor());
        var curso = cursoRepository.getReferenceById(datosRegistroTopico.curso());

        var topico = new Topico(titulo, mensaje, autor, curso);

        topicoRepository.save(topico);

        return new DatosDetallesTopico(topico);

    }


    public Page<DatosRespuestaTopico> listarTopicos(Pageable paginacion) {

        return topicoRepository.listarOrdenadosPorFecha(paginacion).map(DatosRespuestaTopico::new);

    }

    public Page<DatosRespuestaTopico> listarTopicosPorUsuario(Long id, Pageable paginacion) {
        var response = topicoRepository.findByAutorId(id, paginacion).map(DatosRespuestaTopico::new);
        if (response.isEmpty()){
            throw new UsuarioNoEncontradoException("El usuario no tiene tópico a listar");
        }
        return response;
    }

    public Page<DatosRespuestaTopico> listarTopicosPorCurso(Long cursoId, Pageable paginacion) {
        var response = topicoRepository.findByCursoId(cursoId, paginacion).map(DatosRespuestaTopico::new);
        if (response.isEmpty()) {
            throw new UsuarioNoEncontradoException("No hay tópicos para el curso solicitado");
        }
        return response;
    }

    public Page<DatosRespuestaTopico> listarTopicosPorAnio(Integer year, Pageable paginacion) {
        var response = topicoRepository.findAllByYear(year, paginacion).map(DatosRespuestaTopico::new);
        if (response.isEmpty()){
            throw new UsuarioNoEncontradoException("No hay tópicos para el año solicitado");
        }
        return response;
    }

    public Topico modificaTopico(DatosActualizarTopico datosActualizarTopico) {
        if (!topicoRepository.existsById(datosActualizarTopico.id())) {
            throw new UsuarioNoEncontradoException("Tópico inexistente");
        }
        if (datosActualizarTopico.titulo().isEmpty() && datosActualizarTopico.mensaje().isEmpty()) {
            throw new UsuarioNoEncontradoException("No se recibió ningún dato para realizar modificación");
        }
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarTopico(datosActualizarTopico);
        return topico;
    }

    public void eliminaTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new UsuarioNoEncontradoException("Tópico inexistente");
        }
        topicoRepository.deleteById(id);
    }
}
