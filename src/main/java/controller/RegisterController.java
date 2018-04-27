package controller;

import dao.AccessUserTable;
import model.User;
import net.sf.json.JSONObject;


/**
 * Created by wy on 2018/4/27.
 */
public class RegisterController {

    User user;
    public RegisterController(User user){
        this.user=user;
    }

    public JSONObject verify(){

        AccessUserTable accessUserTable = new AccessUserTable();
        int user_ID = accessUserTable.getUser_ID(user.getUser_name(),user.getUser_pass());

        JSONObject jo_send= new JSONObject();
        jo_send.put("action","register");
        jo_send.put("user_ID",user_ID);
        return jo_send;
    }

}
