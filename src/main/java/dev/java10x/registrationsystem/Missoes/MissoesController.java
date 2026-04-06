package dev.java10x.registrationsystem.Missoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    @Autowired
    private MissoesService missoesService;

    // GET - LISTAR
    @GetMapping("/listar")
    public List<MissoesDTO> listarMissoes() {
        return missoesService.listarMissoes();
    }

    // POST - CRIAR
    @PostMapping("/criar")
    public List<MissoesDTO> criarMissao(@RequestBody List<MissoesDTO> missoesDTO) {
        return missoesDTO.stream()
                .map(missoesService::criarMissao)
                .toList();
    }

    // PUT - ALTERAR
    @PutMapping("/alterar/{id}")
    public MissoesDTO alterarMissao(@PathVariable Long id, @RequestBody MissoesDTO missoesDTO) {
        return missoesService.alterarMissao(id, missoesDTO
        );
    }

    // DELETE - DELETAR
    @DeleteMapping("/deletar/{id}")
    public void deletarMissao(@PathVariable Long id) {
        missoesService.deletarMissao(id);
    }
}