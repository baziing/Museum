package com.example.museum;

public class Option {
    private String name;
    private int imageId;
    private int id;

    public Option(String name,int imageId){
        this.name=name;
        this.imageId=imageId;
    }

    public Option(String name,int imageId,int id){
        this.name=name;
        this.imageId=imageId;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public int getImageId(){
        return imageId;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
