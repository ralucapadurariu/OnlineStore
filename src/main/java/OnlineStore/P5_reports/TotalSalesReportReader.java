package OnlineStore.P5_reports;

import OnlineStore.P2_db_connection.Connect;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

public class TotalSalesReportReader {

    public double totalSalesReport(LocalDate date) throws SQLException, ParseException {
        double sum = 0;
        date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
        Connection conn = null;
        PreparedStatement pstmt = null;
        String updateTableSql = "SELECT sum(A.QUANTITY*A.PRICE) AS AMOUNT\n" +
                "        FROM RALUCA_P_ONLINE_ORDERED_ITEMS A\n" +
                "        JOIN RALUCA_P_ONLINE_SHOPPING_CART B ON A.CART_ID=B.CART_ID\n" +
                "        WHERE B.DATE >= ?";

        try {
            conn =  Connect.getDBConnection();
            pstmt = conn.prepareStatement(updateTableSql);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            pstmt.setDate(1, Date.valueOf(date));

            //pstmt.setDate(1, new Date( LocalDate.parse("2018-10-19", formatter).atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli() ));

            ResultSet rs = pstmt.executeQuery();
           while (rs.next()){
               sum = rs.getDouble("amount");
                System.out.println("Total Sales: " + sum);}


            System.out.println("Select query done!");
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
        return sum;
    }



}
