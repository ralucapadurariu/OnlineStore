package OnlineStore.P5_reports;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class TotalSalesReportPdfGenerator {


    public Document generateTotalSalesReport(LocalDate date) throws IOException, DocumentException, SQLException, ParseException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("E:\\Wantsome\\OnlineStoreBackendSystem\\Reports\\TotalSalesReport.pdf"));
        date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();

        document.open();

        addTitle(document);


        PdfPTable table = new PdfPTable(1);
        addTableHeader(table);
        addRows(table, date);
        document.add(table);


        document.close();
        System.out.println("Total sales report generated successfully!");


        return document;

    }

    private static Font font1() {
        Font font = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 18, BaseColor.BLACK);
        return font;
    }

    private static Font font2() {
        Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
        return font;
    }


    private static void addTitle(Document document) throws DocumentException {
        Paragraph p;
        Font font = font1();
        p = new Paragraph("Total sales report", font);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingAfter(40);
        document.add(p);
    }


    private static void addTableHeader(PdfPTable table) {

        table.setSpacingBefore(25);
        table.setSpacingAfter(25);
        Font font = font2();

        PdfPCell c1 = new PdfPCell(new Phrase("Total sales"));
        table.addCell(c1);


    }

    private static void addRows(PdfPTable table, LocalDate date) throws IOException, SQLException, ParseException {
        TotalSalesReportReader reportReader = new TotalSalesReportReader();
        table.addCell(reportReader.totalSalesReport(date)+ " ");

    }
}

