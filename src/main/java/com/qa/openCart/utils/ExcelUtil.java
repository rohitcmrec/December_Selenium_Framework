package com.qa.openCart.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {

    private static final String EXCEL_PATH = "./src/test/resources/testData/userData.xlsx";
    private static Workbook workbook;
    private static Sheet sheet;
    private static int rowCount;
    private static int colCount;

    public static Object[][] getData(String sheetName) {
        Object[][] data = null;
        FileInputStream fp;
        try {
            fp = new FileInputStream(EXCEL_PATH);
            workbook = WorkbookFactory.create(fp);
            sheet = workbook.getSheet(sheetName);
            rowCount = sheet.getLastRowNum();
            colCount = sheet.getRow(0).getLastCellNum();
            data = new Object[rowCount][colCount];

            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
