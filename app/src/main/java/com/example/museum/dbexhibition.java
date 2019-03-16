package com.example.museum;

public class dbexhibition {
    private int exhibitionID;
    private String name;
    private String data;
    private String place;
    private String introduce;
    private String background;
    private String points;

    public dbexhibition(int exhibitionID,String name){
        this.exhibitionID=exhibitionID;
        this.name=name;
    }

    public void setExhibitionID(int exhibitionID) {
        this.exhibitionID = exhibitionID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public int getExhibitionID() {
        return exhibitionID;
    }

    public String getName() {
        return name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getBackground() {
        return background;
    }

    public String getData() {
        return data;
    }

    public String getPlace() {
        return place;
    }

    public String getPoints() {
        return points;
    }
}
