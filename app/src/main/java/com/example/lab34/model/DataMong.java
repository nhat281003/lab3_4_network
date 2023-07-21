package com.example.lab34.model;

public class DataMong {
    String name;
    String price;
    String des;
    String image;

    TypeMong type;

    public DataMong(String name, String price, String des, String image, TypeMong type) {
        this.name = name;
        this.price = price;
        this.des = des;
        this.image = image;
        this.type = type;
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
