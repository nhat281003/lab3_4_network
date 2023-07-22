package com.example.lab34.model;

public class DataMong {
    String name,id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String price;
    String des;
    String image;

    TypeMong type;

    public DataMong(String name,String id, String price, String des, String image, TypeMong type) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.des = des;
        this.image = image;
        this.type = type;
    }

    public DataMong() {
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public TypeMong getType() {
        return type;
    }

    public void setType(TypeMong type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
