package com.example.museum;

public class dbexhibit {
    private int exhibitID;
    private String name;
    private int exhibitionID;
    private String imageUrl;
    private String dynasty;
    private String size;
    private String donor;
    private String introduce;
    private String story;
    private String title1;
    private String content1;
    private String title2;
    private String content2;

    public dbexhibit(int exhibitID,String name){
        this.exhibitID=exhibitID;
        this.name=name;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public void setExhibitID(int exhibitID) {
        this.exhibitID = exhibitID;
    }

    public void setExhibitionID(int exhibitionID) {
        this.exhibitionID = exhibitionID;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getStory() {
        return story;
    }

    public String getSize() {
        return size;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getDonor() {
        return donor;
    }

    public String getName() {
        return name;
    }

    public int getExhibitID() {
        return exhibitID;
    }

    public int getExhibitionID() {
        return exhibitionID;
    }

    public String getContent1() {
        return content1;
    }

    public String getContent2() {
        return content2;
    }

    public String getDynasty() {
        return dynasty;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle1() {
        return title1;
    }

    public String getTitle2() {
        return title2;
    }
}