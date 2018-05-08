package myservlet.models;

public class UserInfo {
       String user_ID = null;
       String user_name = null;
       String user_pass = null;

    public UserInfo(String user_ID) {
        this.user_ID = user_ID;
    }

    public UserInfo(String user_name, String user_pass) {

        this.user_name = user_name;
        this.user_pass = user_pass;
    }


}
