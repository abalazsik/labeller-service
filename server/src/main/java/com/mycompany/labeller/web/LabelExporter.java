package com.mycompany.labeller.web;

import com.mycompany.labeller.commons.roles.Roles;
import com.mycompany.labeller.domain.data.LabelRange;
import com.mycompany.labeller.domain.services.LabelService;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ador
 */
@Component
public class LabelExporter {

    @Autowired
    private LabelService labelService;

    public void exportAll(OutputStream outputStream) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("labels");

        AtomicLong rowId = new AtomicLong(0);

        labelService.getAll(new LabelRange(0, 500), Roles.Admin).forEachOrdered(label -> {
            XSSFRow row = sheet.createRow(rowId.intValue());
            row.createCell(0).setCellValue(label.getId().getValue());
            row.createCell(1).setCellValue(label.getName().getValue());

            if (label.getDescription() != null) {
                row.createCell(2).setCellValue(label.getDescription().getValue());
            }

            if (label.getClassifierData() != null) {
                row.createCell(3).setCellValue(label.getClassifierData().getValue());
            }

            row.createCell(4).setCellValue(label.getCreationDate().getValue().toString());
            row.createCell(5).setCellValue(label.getTechnical().value());
            if (label.getParent() != null) {
                row.createCell(6).setCellValue(label.getParent().getValue());
            }
            rowId.incrementAndGet();
        });

        workbook.write(outputStream);
    }

}
