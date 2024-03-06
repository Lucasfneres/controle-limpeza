package br.com.gtbr.services.impl;

import br.com.gtbr.domain.registro.DadosRegistro;
import br.com.gtbr.domain.registro.Registro;
import br.com.gtbr.repositories.RegristroRepository;
import br.com.gtbr.services.RegistroLimpezaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class RegistroLimpezaServiceImpl implements RegistroLimpezaService {

    private final RegristroRepository registros;

    public String registraTarefa(DadosRegistro dadosRegistro) {
        var registro = new Registro(dadosRegistro);
        registros.save(registro);
        System.out.println(registros);
        return "redirect:/registro";
    }

    public String carregarPaginaListagemRegistro(Model model) {
        model.addAttribute("registros", registros.findAll());
        return "registros/listar-tarefas-feitas";
    }

    public String apagarRegistro(Long id){
        registros.deleteById(id);
        return "registros/listar-tarefas-feitas";
    }

    public String editarRegistro(Long id){
        registros.getReferenceById(id);
         return "redirect:/registro";
    }
}
