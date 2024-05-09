package com.factory.appraisal.factoryService.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class Readfiles {
    @Autowired
    private ResourceLoader resourceLoader;


    public List<String> processExcelFile() throws IOException {

        Resource resource =  resourceLoader.getResource("classpath:Cities_List.xlsx");


        List<String> columnValues = new ArrayList<>();

        try (InputStream inputStream = resource.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0); // Assuming the first sheet is the one to be processed

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();
                Cell cell = row.getCell(0); // Assuming the first column is the one to be extracted

                if (cell != null) {
                    String value = cell.getStringCellValue();
                    columnValues.add(value);
                }
            }
        }

        return columnValues;
    }

}
