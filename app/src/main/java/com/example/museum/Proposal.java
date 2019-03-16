package com.example.museum;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

public class Proposal extends BmobObject {
    private BmobUser author;//作者
    private String authorName;//作者名字
    private String phone;//联系电话

    private String proposal;

    public Proposal(){}
    public Proposal(BmobUser bmobUser,String proposal,String authorName,String phone){
        this.author=bmobUser;
        this.proposal=proposal;
        this.authorName=authorName;
        this.phone=phone;
    }

    public BmobUser getAuthor() {
        return author;
    }

    public String getPhone() {
        return phone;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getProposal() {
        return proposal;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setAuthor(BmobUser author) {
        this.author = author;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }
}
