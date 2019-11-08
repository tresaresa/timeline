package dao;

import java.sql.*;

/**
 * @Author Tresaresa
 * @Date 2019/11/6 18:05
 */
public class DBConn {
    private static String url = "jdbc:mysql://localhost:3306/timeline?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT";
    private static String username = "root";
    private static String password = "123456";
    private static Connection  conn = null;

    public static Connection init(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("open mysql");
            conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(){
        try {
            System.out.println("close mysql");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
