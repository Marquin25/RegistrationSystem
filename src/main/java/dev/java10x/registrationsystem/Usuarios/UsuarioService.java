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

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Listar todos os usuarios
    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Listar todos os meus usuarios por ID
    public  UsuarioModel listarUsuarioPorId(Long id) {
        Optional<UsuarioModel> usuarioPorId = usuarioRepository.findById(id);
        return usuarioPorId.orElse(null);
    }

}
