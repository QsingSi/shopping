package lyons.dao;

import java.sql.Connection;
import java.sql.PrepareStament;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lyons.db.DbClose;
import lyons.db.DbConn;
import lyons.entity.Gsales;

/**
 * 数据库gSales表操作
 * @author lyons(weizq)
 */

public final class GsalesDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * 1.当天卖出的商品
     * @return ArrayList<Gsales>商品信息，包括allSum
     */

    public ArrayList<Gsales> dailyGsales() {
        ArrayList<Gsales> GsalesList = new ArrayList<Gsales>();
        conn = DbConn.getconn();

        //售卖时间=当前时间 trunc(sdate)=trunc(sysdate)单位：天
        //sql语句解释见files/sql/java_sql.sql
        String sql = "select gname,gprice,gnum,allSum from goods,(select gid as salesid, sum(snum) as allSum from gsales where trunc(sdate)=trunc(sysdate) group by gid) where gid = salesid";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String gName = rs.getString(1);
                double gPrice = rs.getDouble(2);
                int gNum = rs.getInt(3);
                int allSnum = rs.getInt("allSum");

                Gsales gsales = new Gsales(gName, gPrice, gNum, allSnum);
                GsalesList.add(gsales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return GsalesList;
    }

    /**
     * 2.购物结算-向sales表中插入商品数据!
     * @param gSales 售卖商品对象
     * @return boolean
     */
    public boolean shoppingSettlement(Gsales gSalse) {
        boolean bool = false;
        conn = DbConn.getconn();
        String sql = "INSERT INTO GSALES(GID,SID,SNUM) VALUE(?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, gSalse.getGId());
            pstmt.setInt(2, gSalse.getSId());
            pstmt.setInt(3, gSalse.getSNum());

            int rs = pstmt.executeUpdate();
            if (rs.next()) {
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.addClose(pstmt, conn);
        }
        return bool;
    }
}