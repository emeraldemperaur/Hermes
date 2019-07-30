package iot.empiaurhouse.hermes.Model;

public class SingleItemModel {

    private String name, bannerimg_url, abouttext, website, phone, email;

    public SingleItemModel() {

    }

    public SingleItemModel(String name, String bannerimg_url,String abouttext, String website, String phone, String email) {
        this.name = name;
        this.bannerimg_url = bannerimg_url;
        this.abouttext = abouttext;
        this.website = website;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return bannerimg_url;
    }

    public void setUrl(String url) {
        this.bannerimg_url = url;
    }

    public String getabouttext() {
        return abouttext;
    }

    public void setabouttext(String abouttext) {
        this.abouttext = abouttext;
    }


    public String getwebsitetext() {
        return website;
    }

    public void setwebsitetext(String website) {
        this.website = website;
    }

    public String getphonetext() {
        return phone;
    }

    public void setphonetext(String phone) {
        this.phone = phone;
    }


    public String getemailtext() {
        return email;
    }

    public void setemailtext(String email) {
        this.email = email;
    }



}
