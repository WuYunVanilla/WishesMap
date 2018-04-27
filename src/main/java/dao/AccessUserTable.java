package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by wy on 2018/4/27.
 */
public class AccessUserTable {
    private Connection con;
    public AccessUserTable(){
        this.con=InitDatabase.getCon();
    }

    public int getUser_ID(String user_name, String user_pass) {
        int user_ID =-1;
        try {
            Statement stmt = (Statement) con.createStatement();
            ResultSet rst = stmt.executeQuery("select user_ID from users where user_name = '" +user_name+"' and user_pass ='"+user_pass+"'");

            while(rst.next()) {
                user_ID =rst.getInt("user_ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user_ID;
    }

}
