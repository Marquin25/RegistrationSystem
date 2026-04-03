package dev.java10x.registrationsystem.Usuarios;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Aqui vai ficar o trabalho logico
@Service // TODO: Vai indicar que é uma classe Service
public class UsuarioService {

    public String helloWorld(String name) {
        return "Meu nome é " + name + ", estou testando o uso do Service e do Autowired";
    }

    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;


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
    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Listar todos os meus usuarios por ID
    public UsuarioModel listarUsuarioPorId(Long id) {
        Optional<UsuarioModel> usuarioPorId = usuarioRepository.findById(id);
        return usuarioPorId.orElse(null);
    }

    // Alterar os dados do usuarios
    public UsuarioModel alterarUsuarioPorId(Long id, UsuarioModel usuarioAlterado) {
        if (usuarioRepository.existsById(id)) {
            usuarioAlterado.setId(id);
            return usuarioRepository.save(usuarioAlterado);
        }
        return null;
    }

    // Deletar usuario (DELETE) DELETE * FROM TB_CADASTRO WHERE id=?;
    // Tem que ser um metodo VOID pois não é preciso retornar nada
    public void deletarUsuarioPorId(Long id) {
        Optional<UsuarioModel> usuarioPorId = usuarioRepository.findById(id);
        usuarioRepository.deleteById(id);
    }

}
