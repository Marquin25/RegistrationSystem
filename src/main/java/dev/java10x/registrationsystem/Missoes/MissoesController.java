package dev.java10x.registrationsystem.Missoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    @Autowired
    private MissoesService missoesService;

    // GET - LISTAR
    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissoes() {
        List<MissoesDTO> listaMissoes = missoesService.listarMissoes();
        if (listaMissoes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listaMissoes);
    }

    // POST - CRIAR
    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missoesDTO) {
        MissoesDTO novaMissao = missoesService.criarMissao(missoesDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missao criada com sucesso: " +  novaMissao + " (ID: " +  novaMissao.getId() + ")");
    }

    // PUT - ALTERAR
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarMissao(@PathVariable Long id, @RequestBody MissoesDTO missoesDTO) {
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
    public ResponseEntity<String> deletarMissao(@PathVariable Long id) {
        if (missoesService.listarMissaoPorId(id) != null) {
            missoesService.deletarMissao(id);
            return ResponseEntity.ok("Id: " + id + " deletado com sucesso!");
        } else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID: " + " não encontrada");
        }
    }
}