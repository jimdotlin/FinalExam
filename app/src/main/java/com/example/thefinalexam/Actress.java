package com.example.thefinalexam;

public class Actress {

    private String name;
    private String cup;
    private String height;
    private String weight;
    private String age;
    private String posterThumbnailUrl;

    public Actress(String name, String cup, String height, String weight, String age, String posterThumbnailUrl) {
        this.name = name;
        this.cup = cup;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.posterThumbnailUrl = posterThumbnailUrl;
    }

    public Actress() {
    }

    public String getPosterThumbnailUrl() {
        return posterThumbnailUrl;
    }

    public void setPosterThumbnailUrl(String posterThumbnailUrl) {
        this.posterThumbnailUrl = posterThumbnailUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCup() {
        return cup;
    }

    public void setCup(String cup) {
        this.cup = cup;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


}
