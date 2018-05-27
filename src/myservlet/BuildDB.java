package myservlet;

import java.sql.*;

public class BuildDB {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String addr = "127.0.0.1";
    private static final String port = "3306";
    private static final String dbname = "wishesmap";
    private static final String user = "root";
    private static final String password = "wy1024";
    private static BuildDB database = null;
    public final Statement stmt;


    private BuildDB(String driver, String addr, String port, String dbname, String user, String password) {
        String url = String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s",
                addr, port, dbname, user, password);
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to find driver");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.err.println("SQLException");
            throw new RuntimeException(e);
        }
    }

    //以后迭代是否要改进synchronized ？？-->to 提高性能！
    public static synchronized BuildDB getDatabase() {
        if (database == null)
            database = new BuildDB(driver, addr, port, dbname, user, password);
        return database;
    }


//    public static void main(String[] args) {
//        String insert = String.format("insert into `users` value (0, '%s', '%s')", "java", "123456");
//        System.out.println(insert);
//    }

}
