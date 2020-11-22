package jdbc;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DAO {

    public static List<Object[]> executeQuery(String sql, Object... objects) {
        List ls = new LinkedList();
        PreparedStatement ptmt = null;
        Connection conn = DB.getConn();
        ResultSet rs = null;
        ResultSetMetaData rsm = null;

        try {
            ptmt = conn.prepareStatement(sql);

            int colcount;
            for(colcount = 0; colcount < objects.length; ++colcount) {
                ptmt.setObject(colcount + 1, objects[colcount]);
            }

            rs = ptmt.executeQuery();
            rsm = rs.getMetaData();
            colcount = rsm.getColumnCount();

            while(rs.next()) {
                Object[] value = new Object[colcount];

                for(int i = 1; i <= colcount; ++i) {
                    value[i - 1] = rs.getObject(i);
                }

                ls.add(value);
            }
        } catch (SQLException var10) {
            var10.printStackTrace();
        }

        DB.close(ptmt);
        DB.close(conn);
        return ls;
    }

    public static void executeUpdate(String sql, Object... objects) {
        Connection conn = null;
        PreparedStatement ptmt = null;

        try {
            conn = DB.getConn();
            ptmt = conn.prepareStatement(sql);

            for(int i = 0; i < objects.length; ++i) {
                ptmt.setObject(i + 1, objects[i]);
            }

            ptmt.executeUpdate();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        DB.close(ptmt);
        DB.close(conn);
    }

    public static List<Object[]> executeQueryOne(String sql, Object obj) {
        List ls = new LinkedList();
        Connection conn = DB.getConn();
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsm = null;

        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setObject(1, obj);
            rs = ptmt.executeQuery();
            rsm = rs.getMetaData();

            while(rs.next()) {
                for(int i = 0; i < rsm.getColumnCount(); ++i) {
                    Object obj1 = rs.getObject(i + 1);
                    ls.add(obj1);
                }
            }
        } catch (SQLException var9) {
            var9.printStackTrace();
        }

        DB.close(ptmt);
        DB.close(conn);
        return ls;
    }
}
