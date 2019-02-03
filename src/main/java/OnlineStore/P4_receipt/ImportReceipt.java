package OnlineStore.P4_receipt;

import OnlineStore.P2_db_connection.Connect;
import com.itextpdf.text.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportReceipt {

    public static void insertReceipt(Document document, int cartID) throws SQLException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String insertTableSql = "INSERT INTO RALUCA_P_ONLINE_RECEIPT (CART_ID, RECEIPT) VALUES (?,?)";

        try {
            conn = Connect.getDBConnection();
            pstmt = conn.prepareStatement(insertTableSql);

            File file = new File("E:\\Wantsome\\OnlineStoreBackendSystem\\Receipts\\Receipt_cartID_no" + cartID + ".pdf");
            FileInputStream inputStream = new FileInputStream(file);

            pstmt.setInt(1, cartID);
            pstmt.setBlob(2, inputStream);

            pstmt.executeUpdate();
            System.out.println("Receipt for cart id " + cartID + " stored in database!");


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
