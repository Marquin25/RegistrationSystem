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
    public MissoesDTO criarMissao(@RequestBody MissoesDTO dto) {
        return missoesService.criarMissao(dto);
    }

    // PUT - ALTERAR
    @PutMapping("/alterar/{id}")
    public MissoesDTO alterarMissao(@PathVariable Long id,
                                    @RequestBody MissoesDTO dto) {
        return missoesService.alterarMissao(id, dto);
    }

    // DELETE - DELETAR
    @DeleteMapping("/deletar/{id}")
    public String deletarMissao(@PathVariable Long id) {
        missoesService.deletarMissao(id);
        return "Missao deletada com sucesso!";
    }
}