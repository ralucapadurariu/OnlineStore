package OnlineStore.P3_populate_db;

import OnlineStore.P1_files.P1_1_customer_file.Customer;
import OnlineStore.P1_files.P1_1_customer_file.CustomersCSVFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportCustomer {

    public void insertCustomer(Connection conn) throws SQLException, IOException {
        PreparedStatement pstmt = null;
        String insertTableSql = "INSERT INTO RALUCA_P_ONLINE_CUSTOMER (customer_id, first_name, last_name, address) VALUES (?,?,?,?)";
        CustomersCSVFile customersCSVFile = new CustomersCSVFile();
        customersCSVFile.readCustomerFromFile();
        try {
            pstmt = conn.prepareStatement(insertTableSql);

            for (Customer customer : customersCSVFile.getCustomerList()) {

                pstmt.setInt(1, customer.getCustomerID());
                pstmt.setString(2, customer.getFirstName());
                pstmt.setString(3, customer.getLastName());
                pstmt.setString(4, customer.getAddress());

                pstmt.addBatch();
            }
            pstmt.executeBatch();

            System.out.println("Record is inserted into RALUCA_P_ONLINE_CUSTOMER table!");

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
