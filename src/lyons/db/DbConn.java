package lyons.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * 连接Oracle数据库
 * @author lyons(weizq)
 */

public final class DbConn {
    public static Connection getconn() {
        Connection conn = null;
        String user = "scott";
        String passwd = "tiger";
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        try {
            Class.forName("oracle.jbdc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}