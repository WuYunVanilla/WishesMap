package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by wy on 2018/4/27.
 */
public class InitDatabase {
    private static Connection con;
    public void init(){
        try {
            //加载数据库驱动类
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try { //通过服务器IP用户名密码连接数据库
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/wishesmap","root","wy1024");
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    public static Connection getCon(){
        return con;
    }

}
