package OnlineStore.P3_populate_db;

import OnlineStore.P1_files.P1_3_shopping_cart_file.ShoppingCart;
import OnlineStore.P1_files.P1_3_shopping_cart_file.ShoppingCartCSVFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportShoppingCart {

    public void insertShoppingCart(Connection conn) throws SQLException, IOException {
        PreparedStatement pstmt = null;
        String insertTableSql = "INSERT INTO RALUCA_P_ONLINE_SHOPPING_CART (cart_id, customer_id, date) VALUES (?,?,?)";

        ShoppingCartCSVFile cartCSVFile = new ShoppingCartCSVFile();
        cartCSVFile.readShoppingCartFromFile();


        try {
            pstmt = conn.prepareStatement(insertTableSql);


            for (ShoppingCart shoppingCart : cartCSVFile.getShoppingCartList()) {

                pstmt.setInt(1, shoppingCart.getCartID());
                pstmt.setInt(2, shoppingCart.getCustomerID());
                pstmt.setDate(3, java.sql.Date.valueOf(shoppingCart.getDate().plusDays(1)));

                pstmt.addBatch();
            }
            pstmt.executeBatch();

            System.out.println("Record is inserted into RALUCA_P_ONLINE_SHOPPING_CART table!");

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
