package com.meditech.service;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.meditech.model.Consulta;

import java.io.FileOutputStream;

public class RecetaPDFService {

    public void generadorPDF(Consulta consulta, String paciente, String doctor) {

        try {

            String ruta="receta.pdf";

            PdfWriter writer=new PdfWriter(ruta);

            PdfDocument pdf=new PdfDocument(writer);

            Document document=new Document(pdf);

            document.add(new Paragraph("RECETA MEDICA"));

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Paciente: "+paciente));
            document.add(new Paragraph("Doctor: "+doctor));
            document.add(new Paragraph("Fecha: "+consulta.getFecha()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Diagnostico"));
            document.add(new Paragraph(consulta.getDiagnostico()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Tratamiento"));
            document.add(new Paragraph(consulta.getTratamiento()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Medicamentos"));
            document.add(new Paragraph(consulta.getMedicamentos()));

            document.close();

            System.out.println("PDF generado correctamente");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
