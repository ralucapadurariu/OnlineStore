package OnlineStore.P3_populate_db;

import OnlineStore.P1_files.P1_4_payment_file.Payment;
import OnlineStore.P1_files.P1_4_payment_file.PaymentsCVSFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportPayment {

    public void insertPayment(Connection conn) throws SQLException, IOException {
        PreparedStatement pstmt = null;
        String insertTableSql = "INSERT INTO RALUCA_P_ONLINE_PAYMENT (payment_id, date, amount, type, cart_id, customer_id) VALUES (?,?,?,?,?,?)";

        try {
            PaymentsCVSFile paymentsCVSFile = new PaymentsCVSFile();

            pstmt = conn.prepareStatement(insertTableSql);

            for (Payment payment : paymentsCVSFile.readPaymentFromFile()) {

                pstmt.setInt(1, payment.getPaymentID());
                pstmt.setDate(2, java.sql.Date.valueOf(payment.getDate().plusDays(1)));
                pstmt.setDouble(3, payment.getAmount());
                pstmt.setString(4, String.valueOf(payment.getType()));
                pstmt.setInt(5, payment.getCartID());
                pstmt.setInt(6, payment.getCustomerID());

                pstmt.addBatch();
            }
            pstmt.executeBatch();

            System.out.println("Record is inserted into RALUCA_P_ONLINE_PAYMENT table!");

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        } finally {

            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }


}
