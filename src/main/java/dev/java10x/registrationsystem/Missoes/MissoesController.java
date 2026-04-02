package dev.java10x.registrationsystem.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController //TODO: Esta roteando a nossa API
@RequestMapping("/missoes") //TODO: Mapear a nossa API
public class MissoesController {

    // GET: Mandar uma requisição para mostrar as missões (READ)
    @GetMapping("/listar")
    public String listarMissao() {
        return "Missao listada com sucesso";
    }

    // POST: Mandar uma requisição para criar as missões (CREATE)
    @PostMapping("/cria")
    public String criarMissao() {
        return "Missao criada com sucesso";
    }

    // PUT: Mandar uma requisição para alterar as missões (UPDATE)
    @PutMapping ("/alterar")
    public String alterarMissao() {
        return "Missao alterada com sucesso";
    }

    // DELETE: Mandar uma requisição para deletar as missões (DELETE)
    @DeleteMapping("/deletar")
    public String deletarMissao() {
        return "Missao deletada com sucesso";
    }

}
