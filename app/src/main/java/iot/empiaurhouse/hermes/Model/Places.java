package iot.empiaurhouse.hermes.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Places {

    @SerializedName("name")
    @Expose
    private String placename;
    @SerializedName("abouttext")
    @Expose
    private String abouttext;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("bannerimg_url")
    @Expose
    private String placebannerimg_url;



    public String getBannerimg_url(){
        return placebannerimg_url;
    }

    public void setBannerimg_url(String placebannerimg_url){
        this.placebannerimg_url = placebannerimg_url;

    }

    public String getPlacename(){
        return placename;
    }

    public void setPlacename(String placename){
        this.placename = placename;
    }


    public String getAbouttext(){
        return abouttext;
    }

    public void setAbouttext(String abouttext){
        this.abouttext = abouttext;
    }

    public String getWebsite(){
        return website;
    }

    public void setWebsite(String website){
        this.website = website;
    }


    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }


    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }


}



