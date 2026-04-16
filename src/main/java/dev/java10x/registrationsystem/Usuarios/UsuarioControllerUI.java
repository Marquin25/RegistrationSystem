package dev.java10x.registrationsystem.Usuarios;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/usuario/ui")
public class UsuarioControllerUI {

    private final UsuarioService usuarioService;

    public UsuarioControllerUI(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        List<UsuarioDTO>  usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "listarUsuario"; // Tem que retonar o nome da pagina que renderiza
    }

    @GetMapping("/deletar/{id}")
    public String deletarUsuario(@PathVariable("id") Long id) {
        usuarioService.deletarUsuarioPorId(id);
        return "redirect:/usuario/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String listarUsuarioPorId(@PathVariable Long id, Model model) {
        UsuarioDTO usuario = usuarioService.listarUsuarioPorId(id);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            return "detalhesUsuario";
        } else  {
            model.addAttribute("mensagem", "Usuario não encontrado");
            return "listarUsuario";
        }
    }

    @GetMapping("/adicionar")
    public String adicionarUsuario(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "adicionarUsuario";
    }

    @PostMapping("/salvar")
    public String salvarUsuario(UsuarioDTO usuario,
                                RedirectAttributes redirectAttributes) {

        usuarioService.criarUsuario(usuario);

        redirectAttributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");

        return "redirect:/usuario/ui/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {

        UsuarioDTO usuario = usuarioService.listarUsuarioPorId(id);

        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            return "adicionarUsuario"; // pode reutilizar o form
        }

        model.addAttribute("mensagem", "Usuário não encontrado");
        return "listarUsuario";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarUsuario(@PathVariable Long id,
                                   UsuarioDTO usuario,
                                   RedirectAttributes redirectAttributes) {

        usuarioService.alterarUsuarioPorId(id, usuario);

        redirectAttributes.addFlashAttribute("mensagem", "Usuário atualizado com sucesso!");

        return "redirect:/usuario/ui/listar";
    }

    @GetMapping("/{id}")
    public String verUsuario(@PathVariable Long id, Model model) {
        UsuarioDTO usuario = usuarioService.listarUsuarioPorId(id);

        if (usuario == null) {
            return "redirect:/usuario/ui/listar";
        }

        model.addAttribute("usuario", usuario);
        return "detalhesUsuario";
    }

}
