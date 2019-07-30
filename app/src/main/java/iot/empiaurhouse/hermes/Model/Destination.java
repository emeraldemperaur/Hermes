package iot.empiaurhouse.hermes.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import iot.empiaurhouse.hermes.Model.Destination;



import java.util.ArrayList;
import java.util.List;

public class Destination {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("weather")
    @Expose
    private String weather;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("countrycode")
    @Expose
    private String countrycode;
    @SerializedName("bannerimg_url")
    @Expose
    private String bannerimg_url;
    @SerializedName("places")
    @Expose
    private List<Places> places = null;


    public String getDestinationName(){
        return name;
    }

    public void setDestinationName(String name){
        this.name = name;

    }


    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;

    }


    public String getWeather(){
        return weather;
    }


    public void setWeather(String weather){
        this.weather = weather;

    }


    public String getCurrency(){
        return currency;
    }

    public void setCurrency(String currency){
        this.currency = currency;
    }


    public String getCountrycode(){

        return countrycode;
    }

    public void setCountrycode(String countrycode){
        this.countrycode = countrycode;
    }


    public String getBannerimg_url(){
        return bannerimg_url;
    }

    public void setBannerimg_url(String bannerimg_url){
        this.bannerimg_url = bannerimg_url;
    }

    public List<Places> getPlaces(){
        return places;
    }


    public void setPlaces(List<Places> places){
        this.places = places;
    }



}











