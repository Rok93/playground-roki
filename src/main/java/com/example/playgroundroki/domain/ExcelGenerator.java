package com.example.playgroundroki.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelGenerator {

    public ByteArrayInputStream generateBy(String[] headerTitles, List rows) throws IOException {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet();
        this.styleHeaders(workbook, sheet, headerTitles);
//        this.fillData(sheet, rows, headerTitles.length);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    private final void styleHeaders(SXSSFWorkbook workbook, SXSSFSheet sheet, String[] headerTitles) {
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setFillBackgroundColor(IndexedColors.GREY_80_PERCENT.getIndex());
        SXSSFRow headerRow = sheet.createRow(0);
        int col = 0;

        for(int var8 = headerTitles.length; col < var8; ++col) {
            SXSSFCell cell = headerRow.createCell(col);
            cell.setCellValue(headerTitles[col]);
            cell.setCellStyle(headerCellStyle);
        }

    }
}
