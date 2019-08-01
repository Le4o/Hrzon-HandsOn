package com.example.surfchampionship;

public class Note {

    private Integer id;
    private Integer fk_wave;
    private Integer note1;
    private Integer note2;
    private Integer note3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFk_wave() {
        return fk_wave;
    }

    public void setFk_wave(Integer fk_wave) {
        this.fk_wave = fk_wave;
    }

    public Integer getNote1() {
        return note1;
    }

    public void setNote1(Integer note1) {
        this.note1 = note1;
    }

    public Integer getNote2() {
        return note2;
    }

    public void setNote2(Integer note2) {
        this.note2 = note2;
    }

    public Integer getNote3() {
        return note3;
    }

    public void setNote3(Integer note3) {
        this.note3 = note3;
    }
}
