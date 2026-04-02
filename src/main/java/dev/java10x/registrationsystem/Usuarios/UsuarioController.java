package dev.java10x.registrationsystem.Usuarios;

import org.springframework.beans.factory.annotation.Autowired;
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
    public UsuarioModel criarUsuario(@RequestBody UsuarioModel usuario) {
        return usuarioService.criarUsuario(usuario) ;
    }

    // Mostrar todos os usuarios (READ)
    @GetMapping("/listar")
    public List<UsuarioModel> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    // Mostrar usuario por ID (READ)
    //@PathVariable serve para pegar o id e colocar na URL, dessa forma fica localhost:8080/lista/ e o id do usuario
    @GetMapping("/listar/{id}")
    public UsuarioModel listarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.listarUsuarioPorId(id);
    }

    // Alterar dados do usuario (UPDATE)
    @PutMapping("/alterar/{id}")
    public UsuarioModel alterarUsuarioPorId(@PathVariable Long id, @RequestBody UsuarioModel usuario) {
        return usuarioService.alterarUsuarioPorId(id, usuario);
    }

    // Deletar usuario (DELETE)
    //@PathVariable serve para pegar o id que o usuario colocar e colocar na URL, dessa forma fica localhost:8080/lista/ e o id do usuario
    @DeleteMapping("/deletar/{id}")
    public void deletarUsuarioPorId(@PathVariable Long id) {
        usuarioService.deletarUsuarioPorId(id);
    }

}
