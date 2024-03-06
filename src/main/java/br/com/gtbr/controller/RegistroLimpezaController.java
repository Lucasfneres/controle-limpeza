package br.com.gtbr.controller;

import br.com.gtbr.domain.registro.DadosRegistro;
import br.com.gtbr.services.RegistroLimpezaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;


@Controller
@RequestMapping("/registro")
@RequiredArgsConstructor
public class RegistroLimpezaController {

    private final RegistroLimpezaService registroLimpezaService;

    @GetMapping("/formulario")
    public String carregarPaginaRegistro(Long id, Model model) {
        if (!Objects.isNull(id)){
            model.addAttribute("id", id);
            registroLimpezaService.editarRegistro(id);
        }
        return "registros/formulario";
    }

    @GetMapping
    public String carregarPaginaListagemRegistro(Model model) {
        registroLimpezaService.carregarPaginaListagemRegistro(model);
        return "registros/listar-tarefas-feitas";
    }

    @PostMapping
    @Transactional
    public String registrarTarefa(DadosRegistro dadosRegistro) {
        try {
            registroLimpezaService.registraTarefa(dadosRegistro);
        }catch (ResponseStatusException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }

        return "redirect:/registro";
    }

    @DeleteMapping
    @Transactional
    public String apagarRegistro(Long id) {
        registroLimpezaService.apagarRegistro(id);
        return "redirect:/registro";
    }
}
