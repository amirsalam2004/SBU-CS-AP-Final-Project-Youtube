package com.example.youtube.Model;

import org.w3c.dom.ls.LSException;

import java.time.LocalDate;
import java.util.ArrayList;

public class Video extends Short{
    private String description;
    private String category;
    public Video(String ID,String idchal,String name,String description,String uplaodTime,
                  Integer duration,Integer like,Integer deslike,Integer view) {
        super(ID,idchal,name,uplaodTime,duration,view);
        this.description = description;
        this.category=category;
    }
    public String getDescription() {
        return description;
    }


    public void editDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category= category;
    }
}
