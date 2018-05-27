package myservlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import myjson.MyJson;
import models.WishInfo;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "wish_detail", urlPatterns = {"/wish_detail"})
public class DetailServlet extends HttpServlet {
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

        // 判断Json字符串是否符合WishInfo的格式
        if (MyJson.error(json.toString(), new TypeToken<WishInfo>() {
        }.getType())) {
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(new WishInfo()));
            out.close();
            return;
        }

        String backJson;
        WishInfo wishDetail = null;
        WishInfo wishInfo = gson.fromJson(json.toString(), WishInfo.class);

        String validWishDetail = String.format(
                "select count(*) as `count`, " +
                        "wish_ID, " +
                        "title, " +
                        "content, " +
                        "location, " +
                        "date, " +
                        "content_finish, " +
                        "date_finish, " +
                        "lat, " +
                        "lng " +

                        "from wishes " +
                        "where wish_ID = %s", wishInfo.wish_ID);
        // 从数据库查询
        try {
            ResultSet rs = stmt.executeQuery(validWishDetail);
            // 只有一条信息
            rs.next();
            int count = rs.getInt("count");
            // 设置要返回的信息


            String wish_ID = rs.getInt("wish_ID")+"";
            double lat = rs.getDouble("lat");
            double lng = rs.getDouble("lng");
            String  title = rs.getString("title");

            String content = rs.getString("content");
            String location = rs.getString("location");
            Date date = rs.getDate("date");
            String content_finish = rs.getString("content_finish");
            Date date_finish = rs.getDate("date_finish");



            wishDetail = (count > 0) ? new WishInfo(wish_ID,lat,lng,title,content,location,date,content_finish,date_finish) : new WishInfo("-1");
            rs.close();
        } catch (SQLException e) {
            System.out.println("failed to query this wish_ID");
            throw new RuntimeException(e);
        } finally {
            br.close();
            backJson = gson.toJson(wishDetail);
            // 返回json
            PrintWriter out = response.getWriter();
            out.write(backJson);
            out.close();
        }
    }
}
