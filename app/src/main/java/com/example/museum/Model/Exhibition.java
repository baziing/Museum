package com.example.museum.Model;

public class Exhibition {
    private String name;
    private int id;

    public Exhibition(String name, int id){
        this.name=name;
        this.id=id;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }
}
