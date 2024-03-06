package br.com.gtbr.services.impl;

import br.com.gtbr.domain.registro.Registro;
import br.com.gtbr.repositories.RegristroRepository;
import br.com.gtbr.services.GeneratePdfService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneratePdfServiceImpl implements GeneratePdfService {

    private final RegristroRepository registros;

    public List<Registro> registrosDoBanco() {
        var dadosBanco = registros.findAll();
        return dadosBanco;
    }

    @Override
    public void generatePdf(HttpServletResponse response, HttpServletRequest request) throws DocumentException, IOException {
        Document documentPdf = new Document();
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachment; filename=registros" + getCurrentWeekDateRange() + ".pdf");
        PdfWriter.getInstance(documentPdf, response.getOutputStream());
        documentPdf.open();
        addReportHeader(documentPdf);
        addTableData(documentPdf);
        documentPdf.close();
    }

    private void addReportHeader(Document document) throws DocumentException {
        Paragraph paragraphTitle = new Paragraph();
        paragraphTitle.setAlignment(Element.ALIGN_CENTER);
        paragraphTitle.add(new Chunk(
                "Relatório de tarefas - Semana " + getCurrentWeekDateRange(),
                new Font(Font.HELVETICA, 20)
        ));
        document.add(paragraphTitle);
        document.add(new Paragraph(" "));
    }


    private void addTableData(Document document) throws DocumentException {
        Paragraph pdfPTable = new Paragraph();
        for (Registro registro : registrosDoBanco()) {
            Paragraph nomeDoAtarefado = new Paragraph();
            nomeDoAtarefado.add(new Chunk(registro.getNome(), new Font(Font.COURIER, 14)));

            Paragraph tarefasFeitas = new Paragraph();
            tarefasFeitas.add("Tarefa: " + registro.getTarefa()
                    + " - Dia: " + registro.getData()
            );
            pdfPTable.add(nomeDoAtarefado);
            pdfPTable.add(tarefasFeitas);

        }
        document.add(pdfPTable);
    }

    private String getCurrentWeekDateRange() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
        String startDay = dateFormat.format(calendar.getTime());

        calendar.add(Calendar.DATE, 4);
        String endDay = dateFormat.format(calendar.getTime());

        return startDay + "--" + endDay;
    }
}