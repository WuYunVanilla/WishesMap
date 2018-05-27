package myservlet;

import com.google.gson.Gson;
import models.UserInfo;
import models.WishInfo;

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

@WebServlet(name = "home", urlPatterns = {"/home"})

public class HomeServlet extends HttpServlet {
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
        UserInfo user = gson.fromJson(json.toString(), UserInfo.class);
        String sql = String.format("select wish_ID,lat,lng,status from wishes where user_ID = '%s'", user.user_ID);
//        System.out.println(json);
        try {
            ResultSet rs = stmt.executeQuery(sql);

            rs.last();
            int rowCount = rs.getRow(); //获得ResultSet的总行数
            WishInfo[] wishes = new WishInfo[rowCount];
            rs.beforeFirst();// 返回第一个（记住不是rs.first()）,不写的话下面的循环里面没值

            int pt =0;
            while(rs.next()) {
                String wish_ID = rs.getInt("wish_ID")+"";

                double lat = rs.getDouble("lat");
                double lng = rs.getDouble("lng");
                int status_int_type  = rs.getInt("status");

                boolean status = (status_int_type == 1);
                WishInfo wish = new WishInfo(wish_ID,lat,lng,status);

                wishes[pt++]=wish;
            }
            backJson = gson.toJson(wishes);

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
