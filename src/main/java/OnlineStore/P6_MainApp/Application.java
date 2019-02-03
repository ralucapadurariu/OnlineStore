package OnlineStore.P6_MainApp;

import OnlineStore.P4_receipt.ReceiptGenerator;
import OnlineStore.P5_reports.TotalSalesReport;
import OnlineStore.P5_reports.TotalSalesReportPdfGenerator;
import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;

public class Application {

    public void displayMenu() {
        System.out.println("(1) Generate receipt");
        System.out.println("(2) Generate report");
        System.out.print("Your selection is: ");
    }

    public void receiptMenu(){
        System.out.println("Insert cart ID: ");
    }

    public void openReceipt(int cartID){
        try {

            if ((new File("E:\\Wantsome\\OnlineStoreBackendSystem\\Receipts\\Receipt_cartID_no" + cartID + ".pdf").exists())) {

                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler E:\\Wantsome\\OnlineStoreBackendSystem\\Receipts\\Receipt_cartID_no" + cartID + ".pdf");
                p.waitFor();

            } else {

                System.out.println("File doesn't exists");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void reportMenu(){
        System.out.println("Insert date (format yyyy-MM-dd): ");
    }

    public void openReport(LocalDate date){
        try {

            if ((new File("E:\\Wantsome\\OnlineStoreBackendSystem\\Reports\\TotalSalesReport.pdf").exists())) {

                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler E:\\Wantsome\\OnlineStoreBackendSystem\\Reports\\TotalSalesReport.pdf");
                p.waitFor();

            } else {

                System.out.println("File doesn't exists");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }




    public Application() throws DocumentException, SQLException, IOException, ParseException {
        Scanner scanner = new Scanner(System.in);

        displayMenu();
        switch (scanner.nextInt()) {
            case 1:
                System.out.println("You picked to generate receipt.");
                receiptMenu();
                int cartID=scanner.nextInt();
                ReceiptGenerator receiptGenerator = new ReceiptGenerator();
                receiptGenerator.generateReceipt(cartID);
                openReceipt(cartID);
                break;
            case 2:
                System.out.println("You picked to generate report.");
                reportMenu();
                String date = scanner.next();
                TotalSalesReportPdfGenerator generator = new TotalSalesReportPdfGenerator();
                generator.generateTotalSalesReport(LocalDate.parse(date));
                openReport(LocalDate.parse(date));
                break;
            default:
                System.err.println("Invalid option.");
                break;
        }
    }

    public static void main(String[] args) throws SQLException, ParseException, DocumentException, IOException {
        new Application();
    }
}





