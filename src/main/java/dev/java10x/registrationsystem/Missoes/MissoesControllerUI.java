package dev.java10x.registrationsystem.Missoes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/missoes/ui")
public class MissoesControllerUI {

    private final MissoesService missoesService;

    public MissoesControllerUI(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // LISTAR
    @GetMapping("/listar")
    public String listarMissoes(Model model) {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        model.addAttribute("missoes", missoes);
        return "listarMissoes";
    }

    // VER DETALHE
    @GetMapping("/{id}")
    public String verMissao(@PathVariable Long id, Model model) {
        MissoesDTO missao = missoesService.listarMissaoPorId(id);
        model.addAttribute("missao", missao);
        return "detalhesMissao";
    }

    // NOVA MISSÃO
    @GetMapping("/adicionar")
    public String adicionarMissao(Model model) {
        model.addAttribute("missao", new MissoesDTO());
        return "formMissao";
    }

    // SALVAR
    @PostMapping("/salvar")
    public String salvarMissao(MissoesDTO missao) {
        missoesService.criarMissao(missao);
        return "redirect:/missoes/ui/listar";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editarMissao(@PathVariable Long id, Model model) {
        MissoesDTO missao = missoesService.listarMissaoPorId(id);
        model.addAttribute("missao", missao);
        return "formMissao";
    }

    // ATUALIZAR
    @PostMapping("/atualizar/{id}")
    public String atualizarMissao(@PathVariable Long id, MissoesDTO missao) {
        missoesService.alterarMissao(id, missao);
        return "redirect:/missoes/ui/listar";
    }

    // DELETAR
    @GetMapping("/deletar/{id}")
    public String deletarMissao(@PathVariable Long id) {
        missoesService.deletarMissao(id);
        return "redirect:/missoes/ui/listar";
    }
}