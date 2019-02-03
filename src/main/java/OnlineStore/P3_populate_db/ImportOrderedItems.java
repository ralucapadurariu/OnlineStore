package OnlineStore.P3_populate_db;

import OnlineStore.P1_files.P1_2_stock_item_file.StockItem;
import OnlineStore.P1_files.P1_3_shopping_cart_file.ShoppingCart;
import OnlineStore.P1_files.P1_3_shopping_cart_file.ShoppingCartCSVFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportOrderedItems {

    public void insertOrderedItems(Connection conn) throws SQLException, IOException {

        PreparedStatement pstmt = null;
        String insertTableSql = "INSERT INTO RALUCA_P_ONLINE_ORDERED_ITEMS (cart_id, item_code, description, price, category, quantity) VALUES (?,?,?,?,?,?)";

        ShoppingCartCSVFile cartCSVFile = new ShoppingCartCSVFile();
        cartCSVFile.readShoppingCartFromFile();


        try {

            pstmt = conn.prepareStatement(insertTableSql);

            for (ShoppingCart shoppingCart : cartCSVFile.getShoppingCartList()) {
                pstmt.setInt(1, shoppingCart.getCartID());

                for (StockItem item : shoppingCart.getCartItems()) {
                    pstmt.setInt(2, item.getItemCode());
                    pstmt.setString(3, item.getDescription());
                    pstmt.setDouble(4, item.getPrice());
                    pstmt.setString(5, String.valueOf(item.getCategory()));
                    pstmt.setInt(6, item.getQuantity());

                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            }

            System.out.println("Record is inserted into RALUCA_P_ONLINE_ORDERED_ITEMS table!");

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
