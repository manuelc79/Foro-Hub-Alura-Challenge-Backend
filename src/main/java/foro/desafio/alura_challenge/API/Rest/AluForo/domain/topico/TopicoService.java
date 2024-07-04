package foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico;

import foro.desafio.alura_challenge.API.Rest.AluForo.domain.curso.CursoRepository;
import foro.desafio.alura_challenge.API.Rest.AluForo.domain.topico.validaciones.ValidaTopico;
import foro.desafio.alura_challenge.API.Rest.AluForo.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        var curso = cursoRepository.getReferenceById(datosRegistroTopico.autor());

        var topico = new Topico(titulo, mensaje, autor, curso);

        System.out.println(titulo + " " +  mensaje +" " + autor + " " + curso );

        topicoRepository.save(topico);

        return new DatosDetallesTopico(topico);

    }


}
