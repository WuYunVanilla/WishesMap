package controller;


import dao.InitDatabase;
import model.User;
import net.sf.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Created by wy on 2018/4/27.
 */
public class ServerController {
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(8888,1000, InetAddress.getByName("127.0.0.1"));

        while (true) {
            Socket sk = ss.accept();//服务器监听对应端口的输入
            ServerThread st = new ServerThread(sk);//创建一个线程，用线程创建一个套接字
            st.start();
        }
    }
}

class ServerThread extends Thread {
    private Socket sk;

    ServerThread(Socket sk) {
        this.sk = sk;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(sk.getInputStream());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            String JsonStr = null;
            byte[] by = new byte[2048];
            int n;
            while ((n = in.read(by)) != -1)
            {
                baos.write(by, 0, n);
            }
            JsonStr = new String(baos.toByteArray(),"GB2312");

            JSONObject jo_recieve =  JSONObject.fromObject(JsonStr);

            //initiate database
            InitDatabase initDatabase = new InitDatabase();
            initDatabase.init();

            JSONObject jo_send=new JSONObject();

            //select explicit controller by key(action)
            if(jo_recieve.get("action").equals("register")) {
                User user = new User();
                user.setUser_name((String) jo_recieve.get("user_name"));
                user.setUser_pass((String) jo_recieve.get("user_pass"));

                RegisterController rc = new RegisterController(user);
                jo_send=rc.verify();
            }

            byte[] jsonByte = jo_send.toString().getBytes("GB2312");
            DataOutputStream output = new DataOutputStream(sk.getOutputStream());
            output.write(jsonByte);
            output.flush();
            sk.shutdownOutput();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
