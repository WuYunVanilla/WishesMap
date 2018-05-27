package myservlet;

import com.google.gson.Gson;
import models.UserInfo;

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

@WebServlet(name = "register", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    public static final long serialVersionUID = 1L;
    private Statement stmt = BuildDB.getDatabase().stmt;
    private Gson gson = new Gson();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
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
        boolean success = false;
        UserInfo user = gson.fromJson(json.toString(), UserInfo.class);
        String validUserName = String.format("select count(*) as `count` from users where user_name = '%s'", user.user_name);
        String insert = String.format("insert into `users` value (0, '%s', '%s')", user.user_name, user.user_pass);
        try {
            ResultSet rs = stmt.executeQuery(validUserName);
            rs.next();
            int count = rs.getInt("count");
//            rs.close();
            if (count > 0)
                success = false;
            else {
                stmt.executeUpdate(insert);
//                stmt.executeUpdate(insert, Statement.RETURN_GENERATED_KEYS);
//                ResultSet rsid = stmt.getGeneratedKeys();
//                rsid.next();
//                // 如果以后需要返回user_ID
//                user_ID = rsid.getInt(1);
                success = true;

            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("failed to query user_name");
            success = false;
            throw new RuntimeException(e);
        } finally {
            br.close();
            backJson = String.format("{\"success\":\"%b\"}", success);
        }
        /*
          返回json
         */
        PrintWriter out = response.getWriter();
        out.write(backJson);
        out.close();
    }
}
