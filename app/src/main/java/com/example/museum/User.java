package com.example.museum;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

//用户信息
public class User extends BmobUser{
////    用户名
    private String name;

    private BmobFile avatar;//头像
//
    public void setName(String username) {
        this.name = username;
    }

    public BmobFile getAvatar(){
        return avatar;
    }

    public void setAvatar(BmobFile avatar){
        this.avatar=avatar;
    }

    public String getName() {
        return name;
    }

}
