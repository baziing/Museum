package com.example.museum;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class Contribution extends BmobObject{
    private BmobUser author;//作者
    private String authorName;//作者名字
    private String phone;//联系电话

    private String exhibitName;//展品名字
    private BmobFile exhibitPicture;//图片
    private String dynasty;//时间朝代
    private String size;//尺寸
    private String donor;//捐赠人
    private String introduce;//介绍
    private String story;//故事
    private String notes;//额外备注

    public Contribution(){}
    public Contribution(BmobUser bmobUser,String authorName,String phone,String exhibitName,String introduce,String story){
        this.author=bmobUser;
        this.authorName=authorName;
        this.phone=phone;
        this.exhibitName=exhibitName;
        this.introduce=introduce;
        this.story=story;
    }

    public void setAuthor(BmobUser author) {
        this.author = author;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

    public void setPhone(String phone){
        this.phone=phone;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public void setExhibitName(String exhibitName) {
        this.exhibitName = exhibitName;
    }

    public void setExhibitPicture(BmobFile exhibitPicture) {
        this.exhibitPicture = exhibitPicture;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getDonor() {
        return donor;
    }

    public String getDynasty() {
        return dynasty;
    }

    public String getExhibitName() {
        return exhibitName;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getNotes() {
        return notes;
    }

    public String getPhone() {
        return phone;
    }

    public String getSize() {
        return size;
    }

    public String getStory() {
        return story;
    }

    public BmobFile getExhibitPicture() {
        return exhibitPicture;
    }

    public BmobUser getAuthor() {
        return author;
    }
}
