package main.wardrobe.entity;


import android.graphics.Bitmap;

import java.util.Date;

import main.constants.Category;

public class Apparel {

    public static long idCounter = 0l;

    public static long getNextId() {
        return idCounter++;
    }

    private Long id;
    private String name;
    private Bitmap image;
    private Category category;
    private Boolean inWash;
    private Integer howWarm;
    private Integer wear;
    private Date lastWahsedDate;
    private Date lastWornDate;
    private String description;

    public Apparel(Long id) {
        this.id = id;
    }

    public Apparel(Integer howWarm, String name, Bitmap image, Category category, Boolean inWash, String description) {
        this.howWarm = howWarm;
        this.name = name;
        this.image = image;
        this.category = category;
        this.inWash = inWash;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getInWash() {
        return inWash;
    }

    public void setInWash(Boolean inWash) {
        this.inWash = inWash;
    }

    public Integer getHowWarm() {
        return howWarm;
    }

    public void setHowWarm(Integer howWarm) {
        this.howWarm = howWarm;
    }

    public Integer getWear() {
        return wear;
    }

    public void setWear(Integer wear) {
        this.wear = wear;
    }

    public Date getLastWahsedDate() {
        return lastWahsedDate;
    }

    public void setLastWahsedDate(Date lastWahsedDate) {
        this.lastWahsedDate = lastWahsedDate;
    }

    public Date getLastWornDate() {
        return lastWornDate;
    }

    public void setLastWornDate(Date lastWornDate) {
        this.lastWornDate = lastWornDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
