package OnlineStore.P4_receipt;

import OnlineStore.P2_db_connection.Connect;

import java.io.*;
import java.sql.*;

public class ReadReceiptFromDB {

    public static void readBlobReceipt(int cartID, Connection conn ) throws SQLException, IOException {//pt verificare

        PreparedStatement pstmt = null;
        String selectSql = "SELECT RECEIPT FROM RALUCA_P_ONLINE_RECEIPT WHERE CART_ID=?";

        try {

            pstmt = conn.prepareStatement(selectSql);
            ResultSet rs = null;

            pstmt.setInt(1, cartID);
            rs = pstmt.executeQuery();

            File file = new File("E:\\Wantsome\\OnlineStoreBackendSystem\\Receipt from DB\\Receipt_cartID_no" + cartID + ".pdf");
            FileOutputStream output = new FileOutputStream(file);

            System.out.println("Writing to file " + file.getAbsolutePath());
            while (rs.next()) {
                InputStream input = rs.getBinaryStream("RECEIPT");
                byte[] buffer = new byte[1024];
                while (input.read(buffer) > 0) {
                    output.write(buffer);
                }
            }

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
