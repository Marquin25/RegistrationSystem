package dev.java10x.registrationsystem.Usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//TODO: Ele vai mapear esse arquivo do java (Controller.java) fazendo q ele vai avisar para o Java q isso é um Controller
@RequestMapping("/usuario") // TODO: É para colocar todas as rotas no mesmo local
public class UsuarioController {

    @Autowired //TODO:O @Autowired pega um objeto pronto e coloca dentro da sua classe sozinho
    private UsuarioService usuarioService;

    @GetMapping("/boasVindas") //TODO: Vai puxar/pegar informações / localhost:8080/boasVindas
   // @PostMapping //TODO: Vai mandar informações
   // @PutMapping //TODO: Vai alterar informações
   // @PatchMapping //TODO: Vai alterar informações
   // @DeleteMapping //TODO: Vai deletar as informaçoes
    public String boasVindas() {
        return usuarioService.helloWorld("Marcus");
    }

    // ==================
    //       CRUD
    // ==================
    //TODO:CRUD: conjunto de operações básicas do sistema
    //Create (Criar), Read (Ler), Update (Atualizar) e Delete (Deletar)
    //Usado para manipular dados no banco

    // Adicionar um novo usuario (CREATE)
    @PostMapping("/criar")
    public ResponseEntity<String> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO novoUsuario = usuarioService.criarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario criado com sucesso: " + novoUsuario.getNome() + " (ID: " + novoUsuario.getId() + ")");
    }

    // Mostrar todos os usuarios (READ)
    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 - sem dados
        }
        return ResponseEntity.ok(usuarios); // 200 - OK
    }

    // Mostrar usuario por ID (READ)
    //@PathVariable serve para pegar o id e colocar na URL, dessa forma fica localhost:8080/lista/ e o id do usuario
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarUsuarioPorId(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.listarUsuarioPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario); // 200
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário com ID " + id + " não encontrado"); // 404 - Não encontrado
        }
    }

    // Alterar dados do usuario (UPDATE)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarUsuarioPorId(@PathVariable Long id, @RequestBody UsuarioDTO usuario) {

        if (usuarioService.listarUsuarioPorId(id) != null) {
            usuarioService.alterarUsuarioPorId(id, usuario);
            return ResponseEntity.ok(
                    "Usuario: " + usuario.getNome() + "\n" +
                            "Email: " + usuario.getEmail() + "\n" +
                            "ID: " + id + "\n" +
                            "CPF: " + usuario.getCpf() + "\n" +
                            "Missoes: " + usuario.getMissoes() + "\n" +
                            "Alterado com sucesso!");

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario com ID: " + id + " não encontrado");
        }
    }

    // Deletar usuario (DELETE)
    //@PathVariable serve para pegar o id que o usuario colocar e colocar na URL, dessa forma fica localhost:8080/lista/ e o id do usuario
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarUsuarioPorId(@PathVariable Long id) {

        if (usuarioService.listarUsuarioPorId(id) != null) {
            usuarioService.deletarUsuarioPorId(id);
            return ResponseEntity.ok("Usuario com ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario com ID: " + id + " não encontrado");
        }
    }

}
