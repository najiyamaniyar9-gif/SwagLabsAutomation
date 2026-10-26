

package com.najiya.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public static Object[][] getExcelData(String filePath,
                                           String sheetName) {

        try (FileInputStream file =
                new FileInputStream(filePath);

             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException(
                    "Excel sheet not found: " + sheetName);
            }

            int totalRows = sheet.getLastRowNum();

            Object[][] data = new Object[totalRows][3];

            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i <= totalRows; i++) {

                Row row = sheet.getRow(i);

                if (row == null) {
                    throw new IllegalArgumentException(
                        "Empty row found: " + (i + 1));
                }

                for (int j = 0; j < 3; j++) {

                    Cell cell = row.getCell(j);

                    data[i - 1][j] =
                        cell == null ? "" :
                        formatter.formatCellValue(cell);
                }
            }

            return data;

        } catch (IOException e) {

            throw new RuntimeException(
                "Unable to read Excel file: " + filePath, e);
        }
    }
}



