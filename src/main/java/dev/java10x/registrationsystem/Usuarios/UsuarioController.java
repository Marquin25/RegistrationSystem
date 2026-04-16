package dev.java10x.registrationsystem.Usuarios;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController//TODO: Ele vai mapear esse arquivo do java (Controller.java) fazendo q ele vai avisar para o Java q isso é um Controller
@RequestMapping("/usuario") // TODO: É para colocar todas as rotas no mesmo local
public class UsuarioController {

    //@Autowired //TODO:O @Autowired pega um objeto pronto e coloca dentro da sua classe sozinho
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/boasVindas") //TODO: Vai puxar/pegar informações / localhost:8080/boasVindas
   // @PostMapping //TODO: Vai mandar informações
   // @PutMapping //TODO: Vai alterar informações
   // @PatchMapping //TODO: Vai alterar informações
   // @DeleteMapping //TODO: Vai deletar as informaçoes
    @Operation(
            summary = "Mensagem de Boas-Vindas",
            description = "Retorna uma mensagem simples de boas-vindas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensagem retornada com sucesso")
    })
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
    @Operation(
            summary = "Cria usuário",
            description = "Cria um novo usuário e salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição(dados inválidos)")
    })
    public ResponseEntity<String> criarUsuario(
            @Parameter(description = "Dados do usuário a ser criado")
            @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO novoUsuario = usuarioService.criarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario criado com sucesso: " + novoUsuario.getNome() + " (ID: " + novoUsuario.getId() + ")");
    }

    // Mostrar todos os usuarios (READ)
    @GetMapping("/listar")
    @Operation(
            summary = "Listar usuários",
            description = "Retorna todos os usuários cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum usuário encontrado")
    })
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
    @Operation(
            summary = "Buscar usuário por ID",
            description = "Retorna um usuário específico com base no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado, verifique se o ID esta correto")
    })
    public ResponseEntity<?> listarUsuarioPorId(
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable Long id) {
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
    @Operation(
            summary = "Atualizar usuário",
            description = "Atualiza os dados de um usuário existente pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<?> alterarUsuarioPorId(
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable Long id,

            @Parameter(description = "Novos dados do usuário")
            @RequestBody UsuarioDTO usuario) {

        UsuarioDTO usuarioAtualizado = usuarioService.alterarUsuarioPorId(id, usuario);

        if (usuarioAtualizado != null) {
            return ResponseEntity.ok(usuarioAtualizado); // retorna objeto atualizado
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario com ID: " + id + " não encontrado");
        }
    }

    // Deletar usuario (DELETE)
    //@PathVariable serve para pegar o id que o usuario colocar e colocar na URL, dessa forma fica localhost:8080/lista/ e o id do usuario
    @DeleteMapping("/deletar/{id}")
    @Operation(
            summary = "Deletar usuário",
            description = "Remove um usuário do sistema com base no ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<String> deletarUsuarioPorId(@PathVariable Long id) {

        UsuarioDTO usuario = usuarioService.listarUsuarioPorId(id);

        if (usuario != null) {
            usuarioService.deletarUsuarioPorId(id);
            return ResponseEntity.ok("Usuario deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario com ID: " + id + " não encontrado");
        }
    }

}
