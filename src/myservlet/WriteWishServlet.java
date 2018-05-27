package myservlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.WishInfo;
import myjson.MyJson;

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

@WebServlet(name = "write_wish", urlPatterns = {"/write_wish"})

public class WriteWishServlet extends HttpServlet {
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
        WishInfo wishContent = gson.fromJson(json.toString(), WishInfo.class);

        String insertSql = String.format(
                "insert into wishes （user_ID,title,lat,lng,location,date） " +
                        "VALUES (%s,%s,%f,%f,%s,%s,%s)",
                wishContent.user_ID, wishContent.title, wishContent.lat, wishContent.lng, wishContent.location, wishContent.date,wishContent.content);

        WishInfo wish_back = null;

        // 插入数据库
        try {
            int result = stmt.executeUpdate(insertSql);
            if (result == 1) {
                //查找wish_ID 不适合于高并发
                String querySql="select max(wish_ID) from wishes";

                ResultSet rs = stmt.executeQuery(querySql);
                String wish_ID = rs.getInt("wish_ID") + "";
                wish_back= new WishInfo();
                wish_back.wish_ID=wish_ID;
            }


        } catch (SQLException e) {
            System.out.println("failed to insert this wish");
            throw new RuntimeException(e);
        } finally {
            br.close();
            backJson = gson.toJson(wish_back);
            // 返回json
            PrintWriter out = response.getWriter();
            out.write(backJson);
            out.close();
        }
    }
}
