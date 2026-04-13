package dev.java10x.registrationsystem.Usuarios;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Aqui vai ficar o trabalho logico
@Service // TODO: Vai indicar que é uma classe Service
public class UsuarioService {

    public String helloWorld(String name) {
        return "Meu nome é " + name + ", estou testando o uso do Service e do Autowired";
    }

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;


    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    // Criar um novo usuario
    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
        UsuarioModel usuario = usuarioMapper.map(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.map(usuario);
    }

    // Listar todos os usuarios
    public List<UsuarioDTO> listarUsuarios() {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::map)
                .collect(Collectors.toList());
    }

    // Listar todos os meus usuarios por ID
    public UsuarioDTO listarUsuarioPorId(Long id) {
        Optional<UsuarioModel> usuarioPorId = usuarioRepository.findById(id);
        return usuarioPorId.map(usuarioMapper::map).orElse(null);
    }

    // Alterar os dados do usuarios
    public UsuarioDTO alterarUsuarioPorId(Long id, UsuarioDTO usuarioDTO) {
        Optional<UsuarioModel> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            UsuarioModel usuarioAlterado = usuarioMapper.map(usuarioDTO);
            usuarioAlterado.setId(id);
            UsuarioModel usuarioSalvo= usuarioRepository.save(usuarioAlterado);
            return usuarioMapper.map(usuarioSalvo);
        }
        return null;
    }

    // Deletar usuario (DELETE) DELETE * FROM TB_CADASTRO WHERE id=?;
    // Tem que ser um metodo VOID pois não é preciso retornar nada
    public void deletarUsuarioPorId(Long id) {
        usuarioRepository.deleteById(id);
    }

}
