package com.example.thefinalexam;

public class Actress {

    private String id;
    private String name;
    private String cup;
    private String height;
    private String age;
    private String posterThumbnailUrl;

    public Actress(String id,String name, String cup, String height, String age, String posterThumbnailUrl) {
        this.id = id;
        this.name = name;
        this.cup = cup;
        this.height = height;
        this.age = age;
        this.posterThumbnailUrl = posterThumbnailUrl;
    }

    public Actress() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


}
