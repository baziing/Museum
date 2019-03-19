package com.example.museum.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.museum.Model.dbexhibit;
import com.example.museum.Model.dbexhibition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DBManager {
    private String DBName="museum.db";
    private Context mContext;

    public DBManager(Context mContext){
        this.mContext=mContext;
    }

    public SQLiteDatabase DBManager(String packName){
        String dbPath="/data/data/"+packName+"/databases/";
        if (!(new File(dbPath+DBName)).exists()){
            File f=new File(dbPath);
            if (!f.exists()){
                f.mkdir();
            }
        }
        try{
            FileOutputStream out=new FileOutputStream(dbPath+DBName);
            InputStream in=mContext.getAssets().open(DBName);

            byte[]buffer=new byte[2014];
            int readBytes=0;
            while ((readBytes=in.read(buffer))!=-1){
                out.write(buffer,0,readBytes);
            }
            in.close();
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return SQLiteDatabase.openOrCreateDatabase(dbPath+DBName,null);
    }

    public dbexhibit dbexhibitQuery(SQLiteDatabase sqLiteDatabase, String[]columns, String selection, String[]selectionArgs){
        dbexhibit dbexhibit=null;
        try{
            String table="dbexhibit";
            Cursor cursor=sqLiteDatabase.query(table,columns,selection,null,null,null,null);
            if (cursor.moveToFirst()){
                int id=cursor.getInt(cursor.getColumnIndex("exhibitID"));
                String name=cursor.getString(cursor.getColumnIndex("name"));
                dbexhibit=new dbexhibit(id,name);
                dbexhibit.setIntroduce(cursor.getString(cursor.getColumnIndex("introduce")));
                dbexhibit.setStory(cursor.getString(cursor.getColumnIndex("story")));
                dbexhibit.setTitle1(cursor.getString(cursor.getColumnIndex("title1")));
                dbexhibit.setContent1(cursor.getString(cursor.getColumnIndex("content1")));
                dbexhibit.setTitle2(cursor.getString(cursor.getColumnIndex("title2")));
                dbexhibit.setContent2(cursor.getString(cursor.getColumnIndex("content2")));
//                cursor.moveToNext();
                cursor.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dbexhibit;
    }

    public dbexhibition dbexhibitionQuery(SQLiteDatabase sqLiteDatabase, String[]columns, String selection, String[]selectionArgs){
        dbexhibition dbexhibition=null;
        try {
            String table="dbexhibition";
            Cursor cursor=sqLiteDatabase.query(table,columns,"exhibitionID=3",null,null,null,null);
            if (cursor.moveToFirst()){
                int id=cursor.getInt(cursor.getColumnIndex("exhibitionID"));
                String name=cursor.getString(cursor.getColumnIndex("name"));

                dbexhibition=new dbexhibition(id,name);
//                cursor.moveToNext();
                cursor.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dbexhibition;
    }
}
