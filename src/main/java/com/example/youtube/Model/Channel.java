package com.example.youtube.Model;

public class Channel {
    private String id;
    private String name;
    private String description;
    private String image_Chane=null;

    private String image_pro=null;

    private String Link;

    private String username;

    public Channel(String id,String name,String description,String username,String image){
        this.id=id;
        this.description=description;
        this.name=name;
        this.username=username;
        this.setImage(image);
    }

    public Channel() {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeName(String name) {
        this.name = name;
        // you should know this is unique
        //add database method
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image_Chane;
    }

    public void setImage(String image) {
        this.image_Chane = image;
    }

    public String getImage_pro() {
        return image_pro;
    }

    public void setImage_pro(String image_pro) {
        this.image_pro = image_pro;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }
}
