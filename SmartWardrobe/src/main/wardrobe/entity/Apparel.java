package main.wardrobe.entity;


import android.graphics.Bitmap;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import main.constants.Category;

public class Apparel implements Serializable {

    public static long idCounter = 0l;
    private static DateFormat df = new SimpleDateFormat("MMM dd, yyyy HH:mm");

    public static long getNextId() {
        return idCounter++;
    }

    private Long id;
    private String imagePath;
    private Category category;
    private Set<String> targets;
    private Boolean inWash;
    private Integer howWarm;
    private Integer wear;
    private Date lastWahsedDate;
    private Date lastWornDate;
    private String description;

    public Apparel(Long id) {
        this.id = id;
    }

    @Deprecated
    public Apparel(String name, Bitmap image, Category category, Integer howWarm, String description) {
        id = getNextId();
    }

    public Apparel(String imagePath, Category category, Integer howWarm, String description, Set<String> targets) {
        this.imagePath = imagePath;
        this.howWarm = howWarm;
        this.category = category;
        this.description = description;
        inWash = false;
        wear = 0;
        id = getNextId();
        this.targets = targets;
    }

    public Apparel(String imagePath, Category category, Integer howWarm, String description) {
        this(imagePath, category, howWarm, description, new HashSet<String>());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    public String getLastWashedDateString() {
        return lastWahsedDate == null ? "" : df.format(lastWahsedDate);
    }

    public void setLastWahsedDate(Date lastWahsedDate) {
        this.lastWahsedDate = lastWahsedDate;
    }

    public Date getLastWornDate() {
        return lastWornDate;
    }

    public String getLastWornDateString() {
        return lastWornDate == null ? "" : df.format(lastWornDate);
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

    public Set<String> getTargets() {
        return targets;
    }

    public void setTargets(Set<String> targets) {
        this.targets = targets;
    }
    
    public void addTarget(String s) {
        this.targets.add(s);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Apparel)
            return id.equals(((Apparel) o).id);
        return false;
    }
}
