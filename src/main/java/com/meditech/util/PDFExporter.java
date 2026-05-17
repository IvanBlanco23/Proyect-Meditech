package com.meditech.util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import com.meditech.model.Paciente;

import java.util.List;

public class PDFExporter {

    public static void exportarPacientes(List<Paciente> pacientes){

        try {

            PdfWriter wirter= new PdfWriter("reporte_pacientes.pdf");

            PdfDocument pdf = new PdfDocument(wirter);

            Document document = new Document(pdf);

            document.add(new Paragraph(
                    "Reporte de Pacientes"
            ));

            document.add(new Paragraph(" "));

            for (Paciente paciente : pacientes) {

                document.add(new Paragraph(
                        "ID: " + paciente.getId()
                        + " | Nombre: " + paciente.getNombre()
                        + " | Edad: " + paciente.getEdad()
                        + " | Telefono: " + paciente.getTelefono()
                ));
            }

            document.close();

            System.out.println("PDF generado");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
