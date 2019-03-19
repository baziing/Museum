package com.example.museum.Model;

public class Exhibit {
    private String name;
    private int id;
    private String content;
    private int img;

    public Exhibit(int id, int img,String name,String content){
        this.name=name;
        this.id=id;
        this.content=content;
        this.img=img;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

    public String getContent() {
        return content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
