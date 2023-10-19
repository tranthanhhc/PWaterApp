package com.example.pwaterapp.model;

public class Water {
    private int id;
    private int type;
    private String brand;


    public Water(int type, String brand) {
        this.type = type;
        this.brand = brand;
    }

    public Water(int id, int type, String brand) {
        this.id = id;
        this.type = type;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
