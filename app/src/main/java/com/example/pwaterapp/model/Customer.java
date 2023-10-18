package com.example.pwaterapp.model;

public class Customer {
    private int id;
    private String name;
    private String address;
    private int water_type;
    private String water_brand;
    private String time;
    private byte[] image;

    public Customer(String name, String address, int water_type, String water_brand, String time, byte[] image) {
        this.name = name;
        this.address = address;
        this.water_type = water_type;
        this.water_brand = water_brand;
        this.time = time;
        this.image = image;
    }

    public Customer(int id, String name, String address, int water_type, String water_brand, String time, byte[] image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.water_type = water_type;
        this.water_brand = water_brand;
        this.time = time;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getWater_type() {
        return water_type;
    }

    public void setWater_type(int water_type) {
        this.water_type = water_type;
    }

    public String getWater_brand() {
        return water_brand;
    }

    public void setWater_brand(String water_brand) {
        this.water_brand = water_brand;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
