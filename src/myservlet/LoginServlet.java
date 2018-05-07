package myservlet;


import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;


@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    public static final long serialVersionUID = 1L;
    private Statement stmt = BuildDB.getDatabase().stmt;
    private Gson gson = new Gson();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        /*
          接收json
         */
        BufferedReader br = request.getReader();
        StringBuilder json = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            json.append(line);
        }

        String backJson = "";
        int user_ID;
        UserInfo user = gson.fromJson(json.toString(), UserInfo.class);
        String sql = String.format("select count(*) as count, user_ID from users where user_name = '%s' and user_pass = '%s'", user.user_name, user.user_pass);
//        System.out.println(json);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            int count = rs.getInt("count");
            if (count > 0)
                user_ID = rs.getInt("user_ID");
            else
                user_ID = -1;
            backJson = String.format("{\"user_ID\":%d}", user_ID);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        br.close();

        /*
          返回json
         */
        PrintWriter out = response.getWriter();
        out.write(backJson);
        out.close();
    }
}
