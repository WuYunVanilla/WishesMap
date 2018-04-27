package model;

/**
 * Created by wy on 2018/4/27.
 */
public class User {
    private int user_ID;
    private String user_name;
    private String user_pass;

    public void setUser_ID(int user_ID){
        this.user_ID=user_ID;
    }

    public void setUser_name(String user_name){
        this.user_name=user_name;
    }

    public void setUser_pass(String user_pass){
        this.user_pass=user_pass;
    }

    public int getUser_ID(){
        return user_ID;
    }

    public String getUser_name(){
        return user_name;
    }

    public String getUser_pass(){
        return user_pass;
    }
}
