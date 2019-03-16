package com.example.museum;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class Collection extends BmobObject {
    private BmobUser user;//作者
    private int id;
    private String name;

    public Collection(BmobUser user,int id,String name){
        this.user=user;
        this.id=id;
        this.name=name;
    }

    public Collection(){

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public BmobUser getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(BmobUser user) {
        this.user = user;
    }

    public void deleteCollection(){
        BmobQuery<Collection> collectionBmobQuery=new BmobQuery<>();
        collectionBmobQuery.addWhereEqualTo("id",this.id);
        collectionBmobQuery.addWhereEqualTo("bmobUser", BmobUser.getCurrentUser());
        collectionBmobQuery.findObjects(new FindListener<Collection>() {
            @Override
            public void done(List<Collection> list, BmobException e) {
                if (e==null){
                    //进行删减
                    String objectId=list.get(0).getObjectId();
                    Collection mcollection=new Collection();
                    mcollection.setObjectId(objectId);
                    mcollection.delete(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e==null){

                            }else {

                            }
                        }
                    });
                }
            }
        });
    }

    public void addCollection(){
        this.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){

                }else {

                }
            }
        });
    }
}
