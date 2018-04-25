package lyons.db;

import java.security.Principal;
import java.sql.Connection;
import java.sql.PrepareStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sun.tools.asm.CatchData;

/**
 * 关闭操作数据库时产生的资源流
 * @author lyons(weizq)
 */

public final class DbClose {
    /**
     * 关闭 添加功能 资源
     * @param pstmt,rs,conn
     */

    public static void addClose(PreparedStatement pstmt, Connection conn) {
        /**
         * 多个catch-try 出发点：安全
         */

        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException el) {
            el.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void queryClose(PreparedStatement pstmt, ResultSet rs, Connection conn) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException el) {
            el.printStackTrace();
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException el) {
            el.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}