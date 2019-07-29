package com.example.surfchampionship;

public class Wave {

    private Integer id;
    private Battery battery;
    private Surfist surfist;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public Surfist getSurfist() {
        return surfist;
    }

    public void setSurfist(Surfist surfist) {
        this.surfist = surfist;
    }

}
