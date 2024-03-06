package br.com.gtbr.controller;

import br.com.gtbr.services.GeneratePdfService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/pdf")
@RequiredArgsConstructor
public class GeradorPdfController {

    private final GeneratePdfService generatePdfService;

    @GetMapping("/generate")
    public void generatePdf(HttpServletResponse response, HttpServletRequest request) throws DocumentException, IOException {
            generatePdfService.generatePdf(response, request);
    }
}
