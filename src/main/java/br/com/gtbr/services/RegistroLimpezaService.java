package br.com.gtbr.services;

import br.com.gtbr.domain.registro.DadosRegistro;
import org.springframework.ui.Model;

public interface RegistroLimpezaService {

    String registraTarefa(DadosRegistro dadosRegistro);

    String carregarPaginaListagemRegistro(Model model);

    String apagarRegistro(Long id);

    String editarRegistro(Long id);

}
