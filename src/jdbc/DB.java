package jdbc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8";
    private static final String user = "root";
    private static final String password = "123456";
    private static final String driver = "com.mysql.jdbc.Driver";

    public DB() {
    }

    public static Connection getConn() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8", "root", "123456");
        } catch (ClassNotFoundException var2) {
            var2.printStackTrace();
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

        return conn;
    }

    public static void close(Object toclose) {
        if (toclose != null) {
            Class c = toclose.getClass();

            try {
                Method m = c.getMethod("close");
                m.invoke(toclose);
            } catch (SecurityException | NoSuchMethodException var3) {
                var3.printStackTrace();
            } catch (IllegalAccessException var4) {
                var4.printStackTrace();
            } catch (IllegalArgumentException var5) {
                var5.printStackTrace();
            } catch (InvocationTargetException var6) {
                var6.printStackTrace();
            }

        }
    }
}
