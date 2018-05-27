package myjson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MyJson {
    private static final Gson gson = new Gson();

    public static boolean error(String json, Type type) {
        try {
            gson.fromJson(json, type);
            return false;
        } catch (Exception e) {
            return true;
        }
    }
    // 重载方法，其中t为.class
    public static <T> boolean error(String json, T t) {
        try {
            Type type = new TypeToken<T>(){}.getType();
            gson.fromJson(json, type);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

//    public static void main(String[] args) {
//        String to = gson.toJson(new Happy(2));
//        System.out.println(error(to, Happy.class));
//    }
}

//class Happy {
//    int happy = 1;
//    Happy(int happy) {
//        this.happy = happy;
//    }
//}
