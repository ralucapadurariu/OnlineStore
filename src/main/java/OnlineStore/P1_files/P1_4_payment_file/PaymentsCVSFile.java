package OnlineStore.P1_files.P1_4_payment_file;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PaymentsCVSFile {

    private final static String FILE_PATH = "E:\\Wantsome\\OnlineStoreBackendSystem\\payments.csv";
    private List<Payment> paymentList = new ArrayList<>();

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public List<Payment> readPaymentFromFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = "";
            br.readLine();//skip header
            while ((line = br.readLine()) != null) {
                paymentList.add(parseLine(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paymentList;

    }

    public Payment parseLine(String line) {
        String[] paymentDetails = line.split(",");

        if (paymentDetails.length == 6) {
            Payment payment = new Payment();
            payment.setPaymentID(Integer.parseInt(paymentDetails[0]));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            payment.setDate(LocalDate.parse(paymentDetails[1]));
            payment.setAmount(Double.parseDouble(paymentDetails[2]));
            payment.setType(PaymentType.typeByCode(paymentDetails[3]));
            payment.setCartID(Integer.parseInt(paymentDetails[4]));
            payment.setCustomerID(Integer.parseInt(paymentDetails[5]));

            return payment;
        }
        return new Payment();

    }

//    public PaymentsCVSFile readPaymentFromFile() throws IOException {
//        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
//            String line = "";
//            br.readLine();//skip header
//            while ((line = br.readLine()) != null) {
//                paymentList.add(parseLine(line));
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return this;
//
//    }



}
