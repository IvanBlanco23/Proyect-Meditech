package com.meditech.service;

import com.meditech.model.Paciente;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelService {

    public void exportarPacientes(List<Paciente> pacientes){

        try (
                Workbook workbook=new XSSFWorkbook()
        ){

            Sheet hoja=workbook.createSheet("Pacientes");

            Row encabezado=hoja.createRow(0);

            encabezado.createCell(0).setCellValue("ID");

            encabezado.createCell(1).setCellValue("Nombre");

            encabezado.createCell(2).setCellValue("Teléfono");

            int fila=1;

            for(Paciente paciente: pacientes){

                Row row=hoja.createRow(fila++);

                row.createCell(0).setCellValue(paciente.getId());

                row.createCell(1).setCellValue(paciente.getNombre());

                row.createCell(2).setCellValue(paciente.getTelefono());

            }

            FileOutputStream fileOut=new FileOutputStream("pacientes.xlsx");

            workbook.write(fileOut);

            fileOut.close();

            System.out.println("Excel exportado");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
