package models;

import java.util.Date;

public class WishInfo {
    public String wish_ID;
    public double lat;
    public double lng;
    public boolean status;
    public String title;
    public String content;
    public String location;
    public Date date;

    public String content_finish;
    public Date date_finish;
    //public Bl
    public String user_ID ;


    public WishInfo(){}

    public WishInfo(String wish_ID){
        this.wish_ID=wish_ID;
    }
    public WishInfo( String wish_ID, double lat, double lng,boolean status){
        this.wish_ID =wish_ID;
        this.lat =lat;
        this.lng =lng;
        this.status =status;
    }

    public WishInfo( String wish_ID, double lat, double lng,String title,String content,String location,Date date, String content_finish,Date date_finish){
        this.wish_ID =wish_ID;
        this.lat =lat;
        this.lng =lng;
        this.title =title;
        this.content =content;
        this.location =location;
        this.date =date;
        this.content_finish=content_finish;
        this.date_finish=date_finish;
    }

}
