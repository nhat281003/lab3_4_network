package com.example.lab34.model;

public class DataMong {
    String name;
    String price;
    String dess;
    String image;

    TypeMong TypeMong;

    public DataMong(String name, String price, String dess, String image, TypeMong TypeMong) {
        this.name = name;
        this.price = price;
        this.dess = dess;
        this.image = image;
        this.TypeMong = TypeMong;
    }

    public TypeMong getType() {
        return TypeMong;
    }

    public void setType(TypeMong TypeMong) {
        this.TypeMong = TypeMong;
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

    public String getDess() {
        return dess;
    }

    public void setDess(String dess) {
        this.dess = dess;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
