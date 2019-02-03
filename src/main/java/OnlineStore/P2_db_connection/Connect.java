package OnlineStore.P2_db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static Connection getDBConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://159.69.118.199:3306/wantsome_java", "wantsomeJava", "r8m4Jb4~");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
