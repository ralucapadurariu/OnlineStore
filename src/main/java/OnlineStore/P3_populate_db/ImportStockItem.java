package OnlineStore.P3_populate_db;

import OnlineStore.P1_files.P1_2_stock_item_file.StockItem;
import OnlineStore.P1_files.P1_2_stock_item_file.ItemCSVFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportStockItem {

    public void insertItem(Connection conn) throws SQLException, IOException {

        PreparedStatement pstmt = null;
        String insertTableSql = "INSERT INTO RALUCA_P_ONLINE_STOCK_ITEM (item_code, description, price, category, quantity) VALUES (?,?,?,?,?)";

        ItemCSVFile itemCSVFile = new ItemCSVFile();
        itemCSVFile.readItemFromFile();
        try {

            pstmt = conn.prepareStatement(insertTableSql);

            for (StockItem item : itemCSVFile.getListOfItems()) {

                pstmt.setInt(1, item.getItemCode());
                pstmt.setString(2, item.getDescription());
                pstmt.setDouble(3, item.getPrice());
                pstmt.setString(4, String.valueOf(item.getCategory()));
                pstmt.setInt(5, item.getQuantity());

                pstmt.addBatch();
            }

            pstmt.executeBatch();
            System.out.println("Record is inserted into RALUCA_P_ONLINE_STOCK_ITEM table!");

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
