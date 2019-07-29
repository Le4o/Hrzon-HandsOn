package com.example.surfchampionship;

public class Battery {

    private Integer id;
    private Surfist surfist1;
    private Surfist surfist2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Surfist getSurfist1() {
        return surfist1;
    }

    public void setSurfist1(Surfist surfist1) {
        this.surfist1 = surfist1;
    }

    public Surfist getSurfist2() {
        return surfist2;
    }

    public void setSurfist2(Surfist surfist2) {
        this.surfist2 = surfist2;
    }

    @Override
    public String toString(){
        return id + " - " + surfist1.getName() + " / " + surfist2.getName();
    }

}
