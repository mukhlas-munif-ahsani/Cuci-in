package com.munifahsan.gowash.Home;

public class LayananModel {
    private int image;
    private int color;
    private String title;
    private String desc;

    public LayananModel(int color, String title, String desc) {
        this.color = color;
        this.title = title;
        this.desc = desc;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
