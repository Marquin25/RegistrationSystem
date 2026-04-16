package dev.java10x.registrationsystem.Missoes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    //@Autowired
    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // GET - LISTAR
    @GetMapping("/listar")
    @Operation(
            summary = "Listar missões",
            description = "Retorna todas as missões cadastradas no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de missões retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma missão encontrada")
    })
    public ResponseEntity<List<MissoesDTO>> listarMissoes() {
        List<MissoesDTO> listaMissoes = missoesService.listarMissoes();
        if (listaMissoes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listaMissoes);
    }

    // POST - CRIAR
    @PostMapping("/criar")
    @Operation(
            summary = "Criar missão",
            description = "Cria uma nova missão no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição (dados inválidos)")
    })
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missoesDTO) {
        MissoesDTO novaMissao = missoesService.criarMissao(missoesDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missao criada com sucesso: " +  novaMissao + " (ID: " +  novaMissao.getId() + ")");
    }

    // PUT - ALTERAR
    @PutMapping("/alterar/{id}")
    @Operation(
            summary = "Atualizar missão",
            description = "Atualiza os dados de uma missão existente pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<String> alterarMissao(

            @Parameter(description = "ID da missão", example = "1")
            @PathVariable Long id,

            @RequestBody
            @Parameter(description = "Novos dados da missão")
            MissoesDTO missoesDTO) {

        if (missoesService.listarMissaoPorId(id) != null) {

            missoesService.alterarMissao(id, missoesDTO);

            return ResponseEntity.ok(
                    "Id: " + id + "\n" +
                            "Nome: " + missoesDTO.getNome() + "\n" +
                            "Dificuldade: " + missoesDTO.getDificuldade() + "\n" +
                            "Alterado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID: " + id + " não encontrada");
        }
    }

    // DELETE - DELETAR
    @DeleteMapping("/deletar/{id}")
    @Operation(
            summary = "Deletar missão",
            description = "Remove uma missão do sistema pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    public ResponseEntity<String> deletarMissao(
            @Parameter(description = "ID da missão", example = "1")
            @PathVariable Long id) {
        if (missoesService.listarMissaoPorId(id) != null) {
            missoesService.deletarMissao(id);
            return ResponseEntity.ok("Id: " + id + " deletado com sucesso!");
        } else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID: " + " não encontrada");
        }
    }
}