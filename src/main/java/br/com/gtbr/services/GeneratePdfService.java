package br.com.gtbr.services;

import br.com.gtbr.domain.registro.Registro;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface GeneratePdfService {

    List<Registro> registrosDoBanco();

    void generatePdf(HttpServletResponse response, HttpServletRequest request) throws DocumentException, IOException;


}
