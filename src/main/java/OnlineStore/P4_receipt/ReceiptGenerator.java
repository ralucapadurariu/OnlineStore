package OnlineStore.P4_receipt;

import OnlineStore.P1_files.P1_2_stock_item_file.StockItem;
import OnlineStore.P1_files.P1_3_shopping_cart_file.ShoppingCart;
import OnlineStore.P1_files.P1_3_shopping_cart_file.ShoppingCartCSVFile;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

public class ReceiptGenerator {


    public Document generateReceipt(int cartID) throws IOException, DocumentException, SQLException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("E:\\Wantsome\\OnlineStoreBackendSystem\\Receipts\\Receipt_cartID_no" + cartID + ".pdf"));

        document.open();

        addTitle(document);
        addNumberAndDate(document, cartID);
        addCustomerDetails(document,cartID);

        PdfPTable table = new PdfPTable(5);
        addTableHeader(table);
        addRows(table, cartID);
        document.add(table);

        addFinalDetails(document, cartID);
        document.close();
        System.out.println("Receipt generated successfully for cart id " + cartID + "!");

        ImportReceipt.insertReceipt(document,cartID);

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

    private static Font font3() {
        Font font = FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.BLACK);
        return font;
    }


    private static void addTitle(Document document) throws DocumentException {
        Paragraph p;
        Font font = font1();
        p = new Paragraph("RECEIPT", font);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingAfter(40);
        document.add(p);
    }

    private static void addNumberAndDate(Document document,int cartID) throws DocumentException, IOException {
        Receipt receipt = new Receipt();

        Font font1 = font2();
        Font font2 = font3();
        Paragraph p1 = new Paragraph("No: " + cartID, font1);

        p1.setAlignment(Element.ALIGN_RIGHT);
        p1.setSpacingAfter(10);
        document.add(p1);


        Paragraph p2 = new Paragraph("Date: " + receipt.generateDate(cartID), font2);
        p2.setAlignment(Element.ALIGN_RIGHT);
        document.add(p2);
    }


    public static void addCustomerDetails(Document document, int cartID) throws DocumentException, IOException {
        Receipt receipt = new Receipt();

        Font font1 = font2();
        Font font2 = font3();
        Paragraph p1 = new Paragraph("Customer " , font1);
        p1.setAlignment(Element.ALIGN_LEFT);
        p1.setSpacingAfter(10);
        document.add(p1);
        Paragraph p2 = new Paragraph("First Name: " + receipt.generateFirstName(cartID), font2);
        p2.setAlignment(Element.ALIGN_LEFT);
        document.add(p2);
        Paragraph p3 = new Paragraph("Last Name: " + receipt.generateLastName(cartID), font2);
        p3.setAlignment(Element.ALIGN_LEFT);
        document.add(p3);
        Paragraph p4 = new Paragraph("Delivery Address: " + receipt.generateAddress(cartID), font2);
        p4.setAlignment(Element.ALIGN_LEFT);
        p4.setSpacingAfter(30);
        document.add(p4);
    }

    private static void addTableHeader(PdfPTable table) {

        table.setSpacingBefore(25);
        table.setSpacingAfter(25);
        Font font = font2();

        PdfPCell c1 = new PdfPCell(new Phrase("No."));
        table.addCell(c1);

        PdfPCell c2 = new PdfPCell(new Phrase("Description"));
        table.addCell(c2);

        PdfPCell c3 = new PdfPCell(new Phrase("Quantity"));
        table.addCell(c3);

        PdfPCell c4 = new PdfPCell(new Phrase("Price"));
        table.addCell(c4);

        PdfPCell c5 = new PdfPCell(new Phrase("Amount"));
        table.addCell(c5);
    }

    private static void addRows(PdfPTable table, int cartID) throws IOException {
        ShoppingCartCSVFile shoppingCartCSVFile = new ShoppingCartCSVFile();
        shoppingCartCSVFile.readShoppingCartFromFile();
        int counter = 1;

        for (ShoppingCart shoppingCart : shoppingCartCSVFile.getShoppingCartList()) {
            if (shoppingCart.getCartID()==cartID) {
                for (StockItem item : shoppingCart.getCartItems()) {
                    table.addCell(counter++ + " ");
                    table.addCell(item.getDescription());
                    table.addCell(item.getQuantity() + "");
                    table.addCell(item.getPrice() + "");
                    double amount = item.getPrice() * item.getQuantity();
                    table.addCell(amount + "");
                }
            }
        }
    }

    private static void addFinalDetails(Document document, int cartID) throws DocumentException, IOException {

        Font font1 = font2();
        Font font2 = font3();
        Receipt receipt = new Receipt();

        Paragraph p1 = new Paragraph(" Total price: " + receipt.totalPrice(cartID));
        p1.setAlignment(Element.ALIGN_LEFT);
        p1.setSpacingAfter(10);
        document.add(p1);

        Paragraph p2 = new Paragraph(" Payment method: Paid with " + receipt.generatePaymentDetails(cartID));
        p2.setAlignment(Element.ALIGN_LEFT);
        p2.setSpacingAfter(10);
        document.add(p2);

    }
}
