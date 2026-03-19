package dev.java10x.registrationsystem.Usuarios;

import org.springframework.web.bind.annotation.*;

@RestController//TODO: Ele vai mapear esse arquivo do java (Controller.java) fazendo q ele vai avisar para o Java q isso é um Controller
@RequestMapping // TODO: É para colocar todas as rotas no mesmo local
public class UsuarioController {

    @GetMapping("/boasVindas") //TODO: Vai puxar/pegar informações / localhost:8080/boasVindas
   // @PostMapping //TODO: Vai mandar informações
   // @PutMapping //TODO: Vai alterar informações
   // @PatchMapping //TODO: Vai alterar informações
   // @DeleteMapping //TODO: Vai deletar as informaçoes
    public String boasVindas() {
        return "Hello World";
    }

}
