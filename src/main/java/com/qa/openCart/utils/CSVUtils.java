package com.qa.openCart.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVUtils {

    private static final String CSV_PATH = "./src/test/resources/testData/data.csv";
    private static List<String[]> rows;

    public static Object[][] getData() {
        CSVReader csvReader;
        try {
            csvReader = new CSVReader(new FileReader(CSV_PATH));
            rows = csvReader.readAll();
            csvReader.close();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
        Object[][] data = new Object[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            data[i] = rows.get(i);
        }
        return data;
    }
}
