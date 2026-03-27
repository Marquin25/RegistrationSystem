package dev.java10x.registrationsystem.Usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/adicionar")
    public String adiconarNinja() {
        return "Ninja adicionada com sucesso";
    }

    // Mostrar todos os usuarios (READ)
    @GetMapping("/listar")
    public String todos() {
        return "Todos os usuarios";
    }

    // Mostrar usuario por ID (READ)
    @GetMapping("/listarID")
    public String mostrarTodosOsUsuarioPorId() {
        return "Usuario por Id ";
    }

    // Alterar dados do usuario (UPDATE)
    @PutMapping("/alterarID")
    public String alterarUsuarioPorId() {
        return "Alterar usuario por Id ";
    }

    // Deletar usuario (DELETE)
    @DeleteMapping("/deletarID")
    public String deletarUsuarioPorId() {
        return "Deletar usuario por Id ";
    }

}
